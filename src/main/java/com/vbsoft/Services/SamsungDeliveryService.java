package com.vbsoft.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.vbsoft.Modeles.In.PKFInfo;
import com.vbsoft.Modeles.Out.GENRES.GENRESDelivery;
import com.vbsoft.Modeles.Out.GENRES.GENRESInfoListDelivery;
import com.vbsoft.Modeles.Repositiries.DeliveryDAO;
import com.vbsoft.Services.scheduled.SamsungASKANSSender;
import com.vbsoft.Utils.SamsungLogger;
import com.vbsoft.Utils.SamsungTools;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@PropertySources({
        @PropertySource("classpath:Constants.properties"),
        @PropertySource(value = "file:${base.path}/config/Constants.properties", ignoreResourceNotFound = true)
})
public class SamsungDeliveryService {

    /**
     * Delivery dao.
     */
    final DeliveryDAO DELIVERY_DAO;

    /**
     * Service tools.
     */
    final SamsungTools SamsungTOOLS;

    /**
     * Лог.
     */
    final SamsungLogger log;

    /**
     * Базовая папка.
     * <p>
     * Системная переменная - "base.path".
     */
    @Value("#{systemProperties['base.path']}")
    String BASE_DIR = new File("").getAbsolutePath();

    /**
     * Максимальное количество попыток отправить ответ по заказу.
     * <p>
     * В файле Constants.properties - "samsung.sent.max.count".
     */
    @Value("${samsung.sent.max.count}")
    Integer TRY_COUNT;

    /**
     * Путь к сохранению XML файлов заказа.
     * <p>
     * В файле Constants.properties - "samsung.delivery.path".
     */
    @Value("${samsung.delivery.path}")
    String DELIVERY_PATH;

    /**
     * Путь к сохранению ошибочных XML файлов заказа.
     * <p>
     * В файле Constants.properties - "samsung.delivery.error".
     */
    @Value("${samsung.delivery.error}")
    String ERROR_PATH;

    /**
     * Путь к сохранению не сериализованных XML файлов заказа.
     * <p>
     * В файле Constants.properties - "samsung.delivery.unserializable".
     */
    @Value("${samsung.delivery.unserializable}")
    String UNSERIALIZABLE_PATH;

    /**
     * Путь к сохранению не сохранных XML файлов заказа.
     * <p>
     * В файле Constants.properties - "samsung.delivery.unsaved".
     */
    @Value("${samsung.delivery.unsaved}")
    String UNSAVED_PATH;

    /**
     * Путь к сохранению XML файлов заказа с неизвестной ошибкой.
     * <p>
     * В файле Constants.properties - "samsung.delivery.unknown".
     */
    @Value("${samsung.delivery.unknown}")
    String UNKNOWN_PATH;

    /**
     * Контекст Spring.
     */
    ApplicationContext CONTEXT;

    /**
     * Конструктор сервиса.
     *
     * @param context Контекст Spring
     */
    @Autowired
    public SamsungDeliveryService(ApplicationContext context) {
        this.CONTEXT = context;
        this.DELIVERY_DAO = context.getBean(DeliveryDAO.class);
        this.SamsungTOOLS = context.getBean(SamsungTools.class);
        this.log = context.getBean(SamsungLogger.class);
    }

    /**
     * Get package item by document number.
     *
     * @param number Document number
     * @return Package item
     */
    public PKFInfo getRequestByDocumentNumber(String number) {
        List<PKFInfo> infoList = this.DELIVERY_DAO.findByDocumentNumber(number).parallelStream().sorted(Comparator.comparing(PKFInfo::getDocumentDate)).collect(Collectors.toList());
        return infoList.get(infoList.size() - 1);
    }

    /**
     * Сохраняет файл с заказом.
     * <p>
     * Для сохранения в конкретную папку
     * нужно ввести системную переменную "base.path".
     * <p>
     * По умолчанию относительный путь от JAR файла.
     *
     * @param REQUEST_BODY Информация о заказе
     * @return Имя файла.
     */
    public String saveDeliveryToFile(final String REQUEST_BODY) {
        String outputDir = this.getGeneratedFileName();
        log.info("Сохранение заказа в файл - '{}'", outputDir);
        Path file = Paths.get(outputDir);
        this.createOrdersDir(file);
        try {
            Files.createFile(file);
            Files.writeString(file, REQUEST_BODY, StandardOpenOption.CREATE);
        } catch (Exception ex) {
            log.error("Не удалось сохранить файл заказа - '{}'", file);
        }

        return file.toString();
    }

    /**
     * Создает папку для сохранения заказа в виде XML файлов.
     *
     * @param file Файл для сохранения
     */
    private void createOrdersDir(Path file) {
        Path dirs = file.getParent();
        if (!Files.exists(dirs)) {
            log.info("Создание родительских папок. Папка - '{}'", dirs);
            try {
                Files.createDirectories(dirs);
            } catch (Exception ex) {
                log.error("Не удалось создать папки для сохранения файлов заказов. Папка - '{}'", dirs);
            }
            log.info("Папка для для сохранения файлов заказов создана");
        } else {
            log.info("Папка для создания файлов заказов существует");
        }
    }

    /**
     * Копирует файл с ошибками в определенные папки.
     *
     * @param file Имя Файл
     * @param dest Папка для сортировки
     */
    private void copyErrorFiles(String file, String dest) {
        Path target = Paths.get(file);
        if (!Files.isRegularFile(target)) {
            log.warn("Путь '{}' не является файлом. Прерывание копирования", target.toString());
            return;
        }
        dest = this.BASE_DIR + dest;
        Path destPath = Paths.get(dest);
        try {
            if (!Files.exists(destPath)) {
                log.info("Создание родительских папок. Папка - '{}'", destPath);
                Files.createDirectories(destPath);
            } else {
                log.info("Папка для создания файлов ошибочных заказов существует");
            }
            Files.copy(target, Paths.get(destPath + "/" + target.getFileName()));
        } catch (Exception ex) {
            log.warn("Не удалось скопировать файл '{}'" +
                            " в папку для сохранения файлов ошибочных заказов. Папка - '{}'. " +
                            "Сообщение:\n {}",
                    target.toString(), destPath, ex.getMessage());
        }
        log.info("Файл заказа с ошибками успешно скопирован. Файл - '{}'", destPath + "/" + target.getFileName());
    }

    public Path getErrors() {
        LinkedList<Path> paths = new LinkedList<>();
        try {
            if (Files.exists(Paths.get(this.BASE_DIR + this.UNSAVED_PATH))) {
                paths.addAll(Files
                        .walk(Paths.get(this.BASE_DIR + this.UNSAVED_PATH))
                        .filter(Files::isRegularFile)
                        .collect(Collectors.toList()));
            }

            if (Files.exists(Paths.get(this.BASE_DIR + this.UNSERIALIZABLE_PATH))) {
                paths.addAll(Files
                        .walk(Paths.get(this.BASE_DIR + this.UNSERIALIZABLE_PATH))
                        .filter(Files::isRegularFile)
                        .collect(Collectors.toList()));
            }

            if (Files.exists(Paths.get(this.BASE_DIR + this.UNKNOWN_PATH))) {
                paths.addAll(Files
                        .walk(Paths.get(this.BASE_DIR + this.UNKNOWN_PATH))
                        .filter(Files::isRegularFile)
                        .collect(Collectors.toList()));
            }
            if (!paths.isEmpty())
                this.zipFiles(paths);
            else
                return null;
        } catch (Exception ex) {
            log.warn("Не удалось создать архив с ошибками. Сообщение:\n '{}'", ex.getMessage());
            return null;
        }


        Path zip = Paths.get(this.BASE_DIR + this.ERROR_PATH + "/errors.zip");
        if (Files.exists(zip)) {
            return zip;
        } else {
            log.warn("Не удалось получить файл с ошибками");
            return null;
        }

    }

    private void zipFiles(LinkedList<Path> files) throws IOException {
        ZipOutputStream stream = new ZipOutputStream(Files.newOutputStream(Paths.get(this.BASE_DIR + this.ERROR_PATH + "/errors.zip")));
        files.forEach(file -> {
            ZipEntry entry = new ZipEntry(file.getParent().getFileName() + "/" + file.getFileName());
            try {
                stream.putNextEntry(entry);
                Files.copy(file, stream);
                stream.closeEntry();
            } catch (IOException e) {
                log.warn("Не удалось создать архив с ошибками. Сообщение:\n '{}'", e.getMessage());
            }
        });
        stream.flush();
        stream.close();
    }

    /**
     * Копирует файл заказа в ошибки сериализации.
     *
     * @param file Файл заказа
     */
    private void createSerializableFile(String file) {
        log.info("Копирование файла '{}' в ошибки сериализации", file);
        this.copyErrorFiles(file, this.UNSERIALIZABLE_PATH);
    }

    /**
     * Копирует файл заказа в ошибки сохранения.
     *
     * @param file Файл заказа
     */
    private void createUnsavedFile(String file) {
        log.info("Копирование файла '{}' в ошибки сохранения", file);
        this.copyErrorFiles(file, this.UNSAVED_PATH);
    }

    /**
     * Копирует файл заказа в необработанные ошибки.
     *
     * @param file Файл заказа
     */
    private void createUnknownFile(String file) {
        log.info("Копирование файла '{}' в необработанные ошибки", file);
        this.copyErrorFiles(file, this.UNKNOWN_PATH);
    }

    /**
     * Генерирует имя файла для сохранения.
     *
     * @return Сгенерированное имя файла вместе с путем сохранения
     */
    private String getGeneratedFileName() {
        log.info("Генерация имени файла");
        int s1 = ThreadLocalRandom.current().nextInt(0, 10);
        int s2 = ThreadLocalRandom.current().nextInt(0, 10);
        int s3 = ThreadLocalRandom.current().nextInt(0, 10);
        int s4 = ThreadLocalRandom.current().nextInt(0, 10);
        int s5 = ThreadLocalRandom.current().nextInt(0, 10);
        final int[] SALT_ARRAY = new int[]{s1, s2, s3, s4, s5};
        String SALT = Arrays.toString(SALT_ARRAY)
                .replaceAll("[\\[\\],\s]", "");
        String fileName = "/deliveries/samsung/%s_%s_SAMSUNG.xml".formatted(new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()), SALT);
        String outputDir = this.BASE_DIR + fileName;
        log.info("Имя файла сгенерированно. Полный путь - '{}'", outputDir);
        return outputDir;
    }

    /**
     * Save sumsung request to file.
     *
     * @param REQUEST_BODY Package item
     * @throws IOException Throws file read exception
     */
    public void saveDeliveryToFile(final PKFInfo REQUEST_BODY) throws IOException {

        log.info("Сохранение файла - ");

        XmlMapper mapper = new XmlMapper();
        String fileName = "/%s_SAMSUNG.xml".formatted(new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()));
        String outputDir = (String) System.getProperties().getOrDefault(
                "user.dir",
                Files.createTempDirectory("Request").toFile().getAbsolutePath());
        mapper.writeValue(Paths.get(outputDir + fileName).toFile(), REQUEST_BODY);
    }

    /**
     * Сериализует и сохраняет заказ.
     *
     * @param order Заказ
     */
    public void saveSamsungStringRequest(String order, String fileName) {
        try {
            XmlMapper mapper = new XmlMapper();
            PKFInfo ser = mapper.readValue(order, PKFInfo.class);
            ser.setFileName(fileName);
            this.saveSamsungRequest(ser);
        } catch (JsonProcessingException json) {
            log.error("Ошибка сериализации заказа. Сообщение:\n {}", json.getMessage());
            this.createSerializableFile(fileName);
        } catch (Exception ex) {
            this.createUnknownFile(fileName);
            log.error("Ошибка сохранения. Не обработанная ошибка. Сообщение: {}", ex.getMessage());
        }
    }

    /**
     * Сохраняет файл в папку с ошибками.
     * <p>
     * Находится в папке: {базовая папка}/samsung/deliveries/error
     *
     * @param fileName Имя файла с путем
     */
    private void saveToError(String fileName) {
        try {
            Path target = Paths.get(fileName);
            Path dest = Paths.get(this.BASE_DIR + "deliveries/samsung/error/" + target.getFileName());
            if (!Files.exists(dest.getParent()))
                Files.createDirectories(dest.getParent());
            Files.copy(target, dest);
        } catch (Exception ex) {
            log.warn("Не удалось перенести файл {} в папку с ошибками", fileName);
        }
    }

    public List<Path> getErrorsFiles() {
        try {
            Path path = Paths.get(this.BASE_DIR + "deliveries/samsung/error/");

            if (!Files.exists(path)) {
                log.warn("Папка с ошибками не существует");
                return new ArrayList<>();
            }

            Stream<Path> files = Files.walk(path);
            if (files.findAny().isEmpty()) {
                log.warn("Папка с ошибками пуста");
                return new ArrayList<>();
            }

            return files.filter(Files::isRegularFile).findAny().stream().collect(Collectors.toList());

        } catch (Exception ex) {
            log.warn("Не удалось получить файлы из папки с ошибками");
            return new ArrayList<>();
        }
    }

    /**
     * Удалить заказ по ID.
     *
     * @param ID ID Заказа
     */
    public boolean deleteOrder(Long ID) {
        Optional<PKFInfo> optional = this.DELIVERY_DAO.findById(ID);
        boolean result = false;
        if (optional.isPresent()) {
            result = true;
            this.deleteOrder(optional.get());
        }
        else
            log.info("Не удалось удалить заказ. Не найдено заказов с ID - '{}'", ID);

        return result;
    }

    /**
     * Удаление заказа по модели.
     *
     * @param INFO Модель заказа
     */
    public void deleteOrder(PKFInfo INFO) {
        log.info("Удаление заказа - '{}'", INFO.getID());
        this.DELIVERY_DAO.delete(INFO);
        log.info("Заказ - {} удален", INFO.getID());
    }

    /**
     * Получает файл заказа по имени.
     *
     * @param fileName Имя файлов
     * @return Запрашиваемый файл
     */
    public File getDeliveryFile(String fileName) {
        try {
            return Paths.get(fileName).toFile();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Получает заказ по ID.
     *
     * @param ID ID заказа
     * @return Информация о заказе
     */
    public PKFInfo getOrderByID(Long ID) {
        log.info("Получение заказа с ID - {}", ID);
        Optional<PKFInfo> optional = this.DELIVERY_DAO.findById(ID);
        if (optional.isPresent()) {
            log.info("Заказ получен");
            return optional.get();
        } else {
            log.info("Заказа с ID - '{}' не найден в БД", ID);
            return null;
        }

    }

    /**
     * Сохраняет серилизаванный заказ
     *
     * @param ORDER Заказ
     */
    public void saveSamsungRequest(@Valid final PKFInfo ORDER) {
        try {
            ORDER.setTryCount(TRY_COUNT);
            ORDER.getBusinessType().setInfo(ORDER);
            ORDER.getDivision().setInfo(ORDER);
            ORDER.getRelatedDocument().setInfo(ORDER);
            ORDER.getRelatedDocumentNumber().setInfo(ORDER);
            ORDER.getTransportationType().setInfo(ORDER);
            ORDER.getTotalCargoInformation().setInfo(ORDER);
            ORDER.getOrgItems().forEach(item -> item.setInfo(ORDER));
            ORDER.getLocItems().forEach(item -> item.setInfo(ORDER));
            ORDER.getRefItems().forEach(item -> item.setInfo(ORDER));
            ORDER.getMatItems().forEach(item -> item.setInfo(ORDER));
            ORDER.getPkgItems().forEach(item -> item.setInfo(ORDER));
            DELIVERY_DAO.save(ORDER);
        } catch (Exception exception) {
            log.error("Не удалось сохранить заказ с номером {}. Сообщение:\n {}", ORDER.getDocumentNumber(), exception.getMessage());
            this.createUnsavedFile(ORDER.getFileName());
        }
    }

    /**
     * Отправляет заказ серверу SAMSUNG вручную.
     *
     * @param ID ID заказа
     * @return Результат
     */
    public boolean sendSuccessMessage(Long ID) {
        PKFInfo info = this.getOrderByID(ID);
        if(info == null)
            return false;

        this.CONTEXT.getBean(SamsungASKANSSender.class).sendASKAN(info, true);
        return true;
    }

    /**
     * Посылает сообщение об ошибке.
     * @param REQUEST_BODY Информация о заказе
     * @param MESSAGE Сообщение об ошибке
     */
    public void sendErrorMessage(final PKFInfo REQUEST_BODY, final String MESSAGE) {
        GENRESDelivery model = new GENRESDelivery();
        model.setMessageSenderIdentifier(REQUEST_BODY.getSenderIdentifier());
        model.setMessageSenderName(REQUEST_BODY.getSenderName());
        model.setReceiverIdentifier(REQUEST_BODY.getReceiverIdentifier());
        model.setMessageSenderName(REQUEST_BODY.getReceiverName());
        model.setMessageNumber(REQUEST_BODY.getNumber());
        model.setDocumentNumber(REQUEST_BODY.getDocumentNumber());
        model.setDocumentDate(REQUEST_BODY.getDocumentDate());
        model.setReferenceMessageNumber(REQUEST_BODY.getDocumentNumber());
        model.setReferenceDocumentNumber(REQUEST_BODY.getDocumentNumber());
        GENRESInfoListDelivery info = new GENRESInfoListDelivery();
        info.setGenres(model);
        info.setSequenceNumber(1);
        info.setInformation(MESSAGE);
        model.getInfo().add(info);
    }
}

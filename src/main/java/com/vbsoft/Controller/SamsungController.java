package com.vbsoft.Controller;

import com.vbsoft.Modeles.In.PKFInfo;
import com.vbsoft.Services.SamsungDeliveryService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Контроллер обмена.
 *
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 */
@CrossOrigin
@RestController
@RequestMapping(
        value = "${spring.profiles.active}")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SamsungController {

    /**
     * Сервис обработки.
     */
    SamsungDeliveryService service;

    /**
     * Конструктор.
     *
     * @param context Контекст Spring
     */
    @Autowired
    public SamsungController(ApplicationContext context) {
        this.service = context.getBean(SamsungDeliveryService.class);
    }

    /**
     * Сохраняет заказ в БД.
     * !!! Основной метод для продуктива !!!
     *
     * @param mod Модель тела запроса
     * @return Ответ клиенту
     */
    @PostMapping
    public ResponseEntity<Object> getMessage(@RequestBody String mod) {
        String file = this.saveFile(mod);
        this.service.saveSamsungStringRequest(mod, file);
        return ResponseEntity.ok().body("processed");
    }

    /**
     * !!! Метод для отладки. Не работает в продуктиве !!!
     * <p>
     * Сохраняет заказ в файл.
     *
     * @param order Заказ
     * @return Статус принятия файла.
     */
    @PostMapping("order")
    public String saveOrder(@RequestBody String order) {
        this.service.saveSamsungStringRequest(order, null);
        return "processed";
    }


    /**
     * !!! Метод для отладки. Не работает в продуктиве !!!
     *
     * Сохраняет заказ в файл.
     *
     * @param order Заказ
     * @return Статус принятия файла.
     */
    @PostMapping("file")
    public String saveFile(@RequestBody String order) {
        return this.service.saveDeliveryToFile(order);
    }

    /**
     * Отправляет ответ ASKANS Samsung вручную.
     * !!! Метод для продуктива !!!
     *
     * @return Ответ клиенту
     */
    @GetMapping(params = {"document"})
    public ResponseEntity<Object> get(@RequestParam() String document) {
        if(document.equalsIgnoreCase("errors")) {
            return this.getErrors();
        } else {
            return this.getXmlFileByID(document);
        }
    }

    private ResponseEntity<Object> getXmlFileByID(String document) {
        PKFInfo info = this.service.getOrderByID(Long.parseLong(document));
        if(info == null) {
            return ResponseEntity
                    .notFound().build();
        } else {
            if(info.getFileName() == null) {
                return ResponseEntity
                        .noContent().build();
            }
            File file = this.service.getDeliveryFile(info.getFileName());
            if(file == null) {
                return ResponseEntity
                        .notFound().build();
            }
            try {
                return ResponseEntity
                        .ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .contentLength(file.length())
                        .body(new InputStreamResource(new FileInputStream(file)));
            } catch (FileNotFoundException e) {
                return ResponseEntity
                        .notFound().build();
            }
        }

    }

    private ResponseEntity<Object> getErrors() {
        Path zip = this.service.getErrors();
        if(zip == null) {
            return ResponseEntity
                    .notFound().build();
        } else {
            try {
                return ResponseEntity
                        .ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + zip.getFileName() + "\"")
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .contentLength(Files.size(zip))
                        .body(new InputStreamResource(Files.newInputStream(zip)));
            } catch (Exception ex) {
                return ResponseEntity
                        .badRequest().build();
            }


        }

    }

    /**
     * Отправляет ответ ASKANS Samsung вручную.
     * !!! Метод для продуктива !!!
     *
     * @return Ответ клиенту
     */
    @GetMapping(params = {"documentFile"})
    public ResponseEntity<Resource> getFile(@RequestParam String ID) {
        PKFInfo info = this.service.getOrderByID(Long.parseLong(ID));
        if(info == null) {
            return ResponseEntity
                    .notFound().build();
        } else {
            if(info.getFileName() == null) {
                return ResponseEntity
                        .noContent().build();
            }
            File file = this.service.getDeliveryFile(info.getFileName());
            if(file == null) {
                return ResponseEntity
                        .notFound().build();
            }
            try {
                return ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .contentLength(file.length())
                        .body(new InputStreamResource(new FileInputStream(file)));
            } catch (FileNotFoundException e) {
                return ResponseEntity
                        .notFound().build();
            }
        }
    }

    /**
     * Delete запрос (неактивен).
     *
     * @return Ответ клиенту
     */
    @DeleteMapping(params = "document")
    public ResponseEntity<Object> delete(@RequestParam(defaultValue = "none", name = "document") String ID) {
        try {
            if(ID.equalsIgnoreCase("none"))
                throw new Exception("Не заполнен параметр 'documentID'");

            Long id = Long.parseLong(ID);
            if(!this.service.deleteOrder(id)) {
                throw new Exception("Не удалось удалить заказ с ID - " + ID);
            }
            return ResponseEntity
                    .ok().body("deleted");
        } catch (Exception ex) {
            return ResponseEntity
                    .badRequest().body(ex.getMessage());
        }
    }

    /**
     * Put запрос (неактивен).
     *
     * @return Ответ клиенту
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @PutMapping
    public ResponseEntity<Object> put() {
        return ResponseEntity
                .status(HttpStatus.NOT_IMPLEMENTED)
                .body("Запрос не активен");
    }

    /**
     * Посылает ASKANS вручную
     *
     * @param ID ID заказа
     * @return Ответ клиенту
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @PatchMapping(params = "document")
    public ResponseEntity<Object> patch(@RequestParam(name = "document") String ID) {
        try {
            Long id = Long.parseLong(ID);
            if (!this.service.sendSuccessMessage(id))
                throw new Exception("Не удалось отправить подтверждение. Информация о заказе не была получена");

            return ResponseEntity
                    .ok().body("ASKANS was sent");
        } catch (Exception ex) {
            return ResponseEntity
                    .badRequest().body(ex.getMessage());
        }
    }

}

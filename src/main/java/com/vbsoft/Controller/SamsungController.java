package com.vbsoft.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.vbsoft.Exceptions.ServiceException;
import com.vbsoft.Modeles.In.PKFInfo;
import com.vbsoft.Services.SamsungDeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Контоллер обмена.
 *
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping(
        value = "${spring.profiles.active}",
        produces = MediaType.TEXT_XML_VALUE,
        consumes = MediaType.TEXT_XML_VALUE)
@Slf4j
public class SamsungController {

    /**
     * Сервис обработки.
     */
    private final SamsungDeliveryService service;

    /**
     * Конструктор.
     * IoC контейнер активен.
     * @param service Сервис обработки
     */
    @Autowired
    public SamsungController(SamsungDeliveryService service) {
        this.service = service;
    }

    /**
     * POST запрос.
     * @param mod Модель тела запроса
     * @return Ответ клиенту
     */
    @PostMapping
    public String getMessage(@RequestBody String mod) {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(() -> {
            this.processRequest(mod);
            service.shutdown();
        }, 1,1,  TimeUnit.SECONDS);
        return "ok";
    }

    private void processRequest(String mod) {
        PKFInfo model = null;
        try {
            ObjectMapper mapper = new XmlMapper();
            model = mapper.readValue(mod,PKFInfo.class);

            this.service.saveDeliveryToFile(model);
            this.service.saveSamsungRequest(model);
            this.service.sendSuccessMessage(model);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            log.error("""
                    Не удалось принять запрос от самсунга.
                    Ошибка разметки json ключей.
                    Сообщение:
                    %s
                    """.formatted(e.getMessage()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("""
                    Не удалось принять запрос от самсунга.
                    Ошибка конвертации json в модель.
                    Сообщение:
                    %s
                    """.formatted(e.getMessage()));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("""
                    Не удалось принять запрос от самсунга.
                    Ошибка чтения записи.
                    Сообщение:
                    %s
                    """.formatted(e.getMessage()));
        } catch (Exception e) {
            log.error("""
                    Не удалось принять запрос от самсунга.
                    Необработанная ошибка.
                    Сообщение:
                    %s
                    """.formatted(e.getMessage()));
            if(model != null)
                this.service.sendErrorMessage(model, e.getMessage());
        }
    }

    /**
     * POST запрос.
     * @param mod Модель тела запроса
     * @return Ответ клиенту
     */
    @PostMapping("only")
    public String getMessageOnly(@RequestBody String mod) throws ServiceException {
        PKFInfo model = null;
        try {
            ObjectMapper mapper = new XmlMapper();
            model = mapper.readValue(mod,PKFInfo.class);

            this.service.saveDeliveryToFile(model);
            this.service.saveSamsungRequest(model);
            return mod;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            log.error("""
                    Не удалось принять запрос от самсунга.
                    Ошибка разметки json ключей.
                    Сообщение:
                    %s
                    """.formatted(e.getMessage()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("""
                    Не удалось принять запрос от самсунга.
                    Ошибка конвертации json в модель.
                    Сообщение:
                    %s
                    """.formatted(e.getMessage()));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("""
                    Не удалось принять запрос от самсунга.
                    Ошибка чтения записи.
                    Сообщение:
                    %s
                    """.formatted(e.getMessage()));
        } catch (Exception e) {
            log.error("""
                    Не удалось принять запрос от самсунга.
                    Необработанная ошибка.
                    Сообщение:
                    %s
                    """.formatted(e.getMessage()));
            throw new ServiceException(Objects.requireNonNull(model), e.getMessage());
        }

        return null;
    }

    /**
     * Get запрос (неактивен).
     * @return Ответ клиенту
     */
    @GetMapping(params = {"document"})
    public String get(@RequestParam String document) throws JsonProcessingException {
        return this.service.sendSuccessMessage(this.service.getRequestByDocumentNumber(document));
    }

    /**
     * Delete запрос (неактивен).
     * @return Ответ клиенту
     */
    @DeleteMapping
    public String delete() throws IOException {
        throw new IOException("test");
    }

    /**
     * Put запрос (неактивен).
     * @return Ответ клиенту
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @PutMapping
    public String put() {
        return null;
    }

    /**
     * Patch запрос (неактивен).
     * @return Ответ клиенту
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @PatchMapping
    public String patch() {
        return null;
    }

}

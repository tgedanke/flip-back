package com.vbsoft.Controller;

import ch.qos.logback.core.util.FileUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.vbsoft.Exceptions.ServiceException;
import com.vbsoft.Modeles.In.PKFInfo;
import com.vbsoft.Modeles.Repositiries.DeliveryDAO;
import com.vbsoft.Services.SamsungDeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Контоллер обмена.
 *
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/test",
        produces = MediaType.TEXT_XML_VALUE,
        consumes = MediaType.TEXT_XML_VALUE)
public class SamsungController {

    @Autowired
    private DeliveryDAO deliveryDAO;

    private final Logger LOG = LoggerFactory.getLogger(SamsungDeliveryService.class);

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
    public String getMessage(@RequestBody String mod) throws ServiceException {
        PKFInfo model = null;
        try {
            ObjectMapper mapper = new XmlMapper();
            model = mapper.readValue(mod,PKFInfo.class);

            this.service.saveDeliveryToFile(model);
            this.service.saveSamsungRequest(model);
            return this.service.sendSuccessMessage(model);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            LOG.error("""
                    Не удалось принять запрос от самсунга.
                    Ошибка разметки json ключей.
                    Сообщение:
                    %s
                    """.formatted(e.getMessage()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            LOG.error("""
                    Не удалось принять запрос от самсунга.
                    Ошибка конвертации json в модель.
                    Сообщение:
                    %s
                    """.formatted(e.getMessage()));
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("""
                    Не удалось принять запрос от самсунга.
                    Ошибка чтения записи.
                    Сообщение:
                    %s
                    """.formatted(e.getMessage()));
        } catch (Exception e) {
            LOG.error("""
                    Не удалось принять запрос от самсунга.
                    Необработанная ошибка.
                    Сообщение:
                    %s
                    """.formatted(e.getMessage()));
            throw new ServiceException(model, e.getMessage());
        }

        return null;
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
            LOG.error("""
                    Не удалось принять запрос от самсунга.
                    Ошибка разметки json ключей.
                    Сообщение:
                    %s
                    """.formatted(e.getMessage()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            LOG.error("""
                    Не удалось принять запрос от самсунга.
                    Ошибка конвертации json в модель.
                    Сообщение:
                    %s
                    """.formatted(e.getMessage()));
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("""
                    Не удалось принять запрос от самсунга.
                    Ошибка чтения записи.
                    Сообщение:
                    %s
                    """.formatted(e.getMessage()));
        } catch (Exception e) {
            LOG.error("""
                    Не удалось принять запрос от самсунга.
                    Необработанная ошибка.
                    Сообщение:
                    %s
                    """.formatted(e.getMessage()));
            throw new ServiceException(model, e.getMessage());
        }

        return null;
    }

    /**
     * Get запрос (неактивен).
     * @return Ответ клиенту
     */
    @GetMapping(value = "/{document}")
    public String get(@PathVariable String document) {
        PKFInfo doс = this.service.getRequestByDocumentNumber(document);
        this.service.saveSamsungRequest(doс);
        return document;
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

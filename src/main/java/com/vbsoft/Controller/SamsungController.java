package com.vbsoft.Controller;

import ch.qos.logback.core.util.FileUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.vbsoft.Exceptions.ServiceException;
import com.vbsoft.Modeles.In.PKFInfo;
import com.vbsoft.Services.SamsungDeliveryService;
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
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new ServiceException(model, e.getMessage());
        }

        return null;
    }

    /**
     * Get запрос (неактивен).
     * @return Ответ клиенту
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @GetMapping
    public String get() {
        return null;
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

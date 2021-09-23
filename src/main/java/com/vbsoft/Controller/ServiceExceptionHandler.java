package com.vbsoft.Controller;

import com.vbsoft.Exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Контроллер обработки исключений.
 *
 * @author vd.zinovev
 * @version 1.0
 * @since 1.0
 *
 */
@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Обработчик ошибок сервиса.
     * @param io Исключение сервиса
     * @return Ответ клиенту
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> handleTest(ServiceException io) {
            return new ResponseEntity<>(io.getModel(), HttpStatus.CONFLICT);
    }

}

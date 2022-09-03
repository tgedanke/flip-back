package com.vbsoft.Utils;

import com.vbsoft.Modeles.Repositiries.SamsungErrorRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Logger сервиса.
 *
 * @author vd.zinovev
 */
@Log4j2
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SamsungLogger {

    /**
     * Контекст Spring.
     */
    ApplicationContext CONTEXT;

    /**
     * Конструктор.
     * @param context Контекст
     */
    @Autowired
    public SamsungLogger(ApplicationContext context) {
        this.CONTEXT = context;
    }

    /**
     * Информативное сообщение с форматированием строки.
     * @param message Сообщение
     * @param params Параметры
     */
    public void info(String message, Object... params) {
        log.info(message, params);
    }

    /**
     * Информативное сообщение.
     * @param message Сообщение
     */
    public void info(String message) {
        log.info(message);
    }

    /**
     * Предупреждающее сообщение с форматированием строки.
     * @param message Сообщение
     * @param params Параметры
     */
    public void warn(String message, Object... params) {
        log.warn(message, params);
    }

    /**
     * Предупреждающее сообщение.
     * @param message Сообщение
     */
    public void warn(String message) {
        log.warn(message);
    }


    /**
     * Отладочное сообщение.
     * @param message Сообщение
     */
    public void debug(String message) {
        log.debug(message);
    }

    /**
     * Сообщение об ошибке.
     * @param message Сообщение
     */
    public void error(String message) {
        this.error(message, null, null);
    }

    /**
     * Сообщение об ошибке.
     * @param message Сообщение
     */
    public void error(String message, Object... params) {
        this.error(message, null, params);
    }

    public void error(@NonNull String message, Throwable throwable) {
        this.error(message, throwable, null);

    }
    /**
     * Сообщение об ошибке.
     * @param message Сообщение
     * @param throwable Исключение
     */
    public void error(@NonNull String message, Throwable throwable, Object... params) {
        if (throwable == null)
            if(params == null)
                log.error(message);
            else
                log.error(message, params);
        else
            log.error(message, throwable);
        SamsungErrorRepository error = this.CONTEXT.getBean(SamsungErrorRepository.class);
        error.createError(String.format(message.replaceAll("\\{}", "%s"), params));
    }
}

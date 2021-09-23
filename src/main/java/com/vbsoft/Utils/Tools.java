package com.vbsoft.Utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.reflections.Reflections;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * Инструменты сервиса.
 *
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Tools {

    /**
     * Получения контекста конфигураций Spring.
     * @return Контектст
     */
    public static AnnotationConfigApplicationContext getApplicationConfigContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        new Reflections("com.vbsoft.Configs.Service")
                .getTypesAnnotatedWith(Configuration.class, true)
                .forEach(context::register);
        context.refresh();
       return context;
    }
}

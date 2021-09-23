package com.vbsoft.Configs.Service;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Конфигурация песочницы.
 *
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 *
 */
@Configuration("ServiceConfig")
@Profile("debug")
public class DebugServiceConfig extends ServiceConfig {

    /**
     * Получает навание файла конфигурации Hibernate в зависимости от профиля.
     *
     * @return Файл конфигурации
     */
    @Override
    public String getHibernateConfigFileName() {
        return "debug.hibernate.cfg.xml";
    }
}

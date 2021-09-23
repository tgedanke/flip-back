package com.vbsoft.Configs.Service;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Конфигурация продуктива.
 *
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 *
 */
@Configuration("ServiceConfig")
@Profile("production")
public class ProductionServiceConfig extends ServiceConfig {

    /**
     * Получает навание файла конфигурации Hibernate в зависимости от профиля.
     *
     * @return Файл конфигурации
     */
    @Override
    public String getHibernateConfigFileName() {
        return "hibernate.cfg.xml";
    }
}

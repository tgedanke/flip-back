package com.vbsoft.Configs.Service;

public abstract class ServiceConfig {

    /**
     * Получает навание файла конфигурации Hibernate в зависимости от профиля.
     * @return Файл конфигурации
     */
    public abstract String getHibernateConfigFileName();
}

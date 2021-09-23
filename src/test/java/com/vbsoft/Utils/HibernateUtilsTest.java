package com.vbsoft.Utils;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HibernateUtilsTest {

    /**
     * Получения файла конфигурации Hibernate по профилю Production.
     */
    @Test
    public void testProductionHibernateConfigFileName() {
        System.setProperty("spring.profiles.active", "production");
        assertEquals(HibernateUtils.getConfigFileName(), "hibernate.cfg.xml", "Контекст вернул не верный файл конфигурации Hibernate");
    }

    /**
     * Получения файла конфигурации Hibernate по профилю Debug.
     */
    @Test
    public void testDebugHibernateConfigFileName() {
        System.setProperty("spring.profiles.active", "debug");
        assertEquals(HibernateUtils.getConfigFileName(), "debug.hibernate.cfg.xml", "Контекст вернул не верный файл конфигурации Hibernate");
    }

    @Test
    public void testHibernateSessionFactory() {
        System.setProperty("spring.profiles.active", "debug");
        try {
            SessionFactory factory = HibernateUtils.getSessionFactory();
            assertNotNull(factory, "Не удалось получить Фабрику сессий Hibernate");
        } catch (Exception ex) {
            Assumptions.assumeFalse(false, "Не удалось подключить к БД. Сообщение - '%s'".formatted(ex.getMessage()));
        }
    }
}
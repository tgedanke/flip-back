package com.flippost;

import com.flippost.Tools.hibernate.HiberanteSessionFactory;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestHibernate {

    private static Session sSession;

    @BeforeAll
    public static void start() {
        sSession = HiberanteSessionFactory.getSessionFactory().openSession();

    }

    @Test
    public void testConnection() {
        Assertions.assertNotNull(sSession, "Не удалось построить сессию с БД");
        Assertions.assertTrue(sSession.isOpen(), "Не удалось открыть соединеие");
        Assertions.assertTrue(sSession.isConnected(), "Не удалось подключиться к БД");
    }

    @AfterAll
    public static void end() {
        sSession.close();
        Assertions.assertFalse(sSession.isConnected(), "Не удалось закрыть соединение с БД");
        Assertions.assertFalse(sSession.isOpen(), "Не удалось закрыть сессию");
    }


}

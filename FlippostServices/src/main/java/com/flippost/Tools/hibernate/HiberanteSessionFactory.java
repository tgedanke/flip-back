package com.flippost.Tools.hibernate;

import com.flippost.Tools.RuleEngine.RuleEngine;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HiberanteSessionFactory {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        return (sessionFactory != null)
                ? sessionFactory
                : buildSessionFactory();
    }

    private static SessionFactory buildSessionFactory() {
        final String HIBERNATE_CONFIG_FILE = RuleEngine.hibernateFileRule();
        final String HIBERNATE_CONFIG_PATH =
                RuleEngine.workPathRule()
                + String.format("\\Flippost"
                        + "\\Configs"
                        + "\\Hibernate\\%s", HIBERNATE_CONFIG_FILE);

        Configuration configuration = new Configuration();
        configuration.configure(new File(HIBERNATE_CONFIG_PATH));
        sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }
}

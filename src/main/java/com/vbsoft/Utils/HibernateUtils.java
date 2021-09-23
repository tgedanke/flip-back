package com.vbsoft.Utils;

import com.vbsoft.Configs.Service.ServiceConfig;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import javax.persistence.Entity;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Hibernate.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HibernateUtils {

    /**
     * Логгер.
     */
    private static final Logger log = Logger.getLogger("Root");

    /**
     * Фабрика сессий.
     */
    private static SessionFactory factory;

    /**
     * Получение фабрики.
     * @return Фабрика сессий
     */
    public static SessionFactory getSessionFactory() {
        if(factory == null) {
            Configuration config = new Configuration().configure(getConfigFileName());
            Reflections reflections = new Reflections("com.vbsoft.Modeles.In");
            Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Entity.class);
            classes.addAll(new Reflections("com.vbsoft.Modeles.Out.ACKANSD").getTypesAnnotatedWith(Entity.class, true));
            classes.addAll(new Reflections("com.vbsoft.Modeles.Out.GENRES").getTypesAnnotatedWith(Entity.class, true));
            classes.addAll(new Reflections("com.vbsoft.Modeles.Out.TRKINF").getTypesAnnotatedWith(Entity.class, true));
            classes.forEach(config::addAnnotatedClass);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
            factory = config.buildSessionFactory(builder.build());
        }

        return factory;
    }

    /**
     * Возвращает имя файла конфигурации Hibernate.
     * @return Имя файла конфигурации
     */
    public static String getConfigFileName() {
        return Rules.configFileRule();
    }

    /**
     * Hibernate config rules.
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private final static class Rules {

        /**
         * Правило получения файла конфигурации Hibernate.
         * @return Имя файла конфигурации
         */
        public static String configFileRule() {
            ServiceConfig config = (ServiceConfig) Tools.getApplicationConfigContext().getBean("ServiceConfig");
            return config.getHibernateConfigFileName();
        }
    }
}

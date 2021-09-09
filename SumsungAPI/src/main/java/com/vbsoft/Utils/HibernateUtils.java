package com.vbsoft.Utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.springframework.boot.autoconfigure.domain.EntityScanner;

import javax.persistence.Entity;
import java.util.Set;

/**
 * Hibernate.
 * @author vd.zinovev
 * @since 1.0
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HibernateUtils {

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
            Configuration config = new Configuration().configure();
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
}

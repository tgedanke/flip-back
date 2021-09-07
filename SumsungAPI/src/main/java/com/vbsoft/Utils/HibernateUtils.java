package com.vbsoft.Utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

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
            config.addPackage("com.vbsoft.Modeles.In");
            config.addPackage("com.vbsoft.Modeles.Out.ACKANSD");
            config.addPackage("com.vbsoft.Modeles.Out.GENRES");
            config.addPackage("com.vbsoft.Modeles.Out.TRKINF");
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
            factory = config.buildSessionFactory(builder.build());
        }

        return factory;
    }
}

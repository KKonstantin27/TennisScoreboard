package utils;

import models.Match;
import models.Player;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtils {
    private static SessionFactory sessionFactory;

    private static void createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure().addAnnotatedClass(Player.class).addAnnotatedClass(Match.class);
        sessionFactory = configuration.buildSessionFactory();
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {createSessionFactory();}
        return sessionFactory;
    }
}

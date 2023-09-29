package utils;

import models.Match;
import models.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class DBUtil {
    private static SessionFactory sessionFactory;

    private static void createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure().addAnnotatedClass(Player.class).addAnnotatedClass(Match.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            createSessionFactory();
        }
        return sessionFactory;
    }

    public static void initDB() {
        getSessionFactory();
        try (InputStream inputStream = DBUtil.class.getResourceAsStream("/init.sql");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query;
            while (((query = reader.readLine()) != null)) {
                session.createNativeQuery(query).executeUpdate();
            }
            session.getTransaction().commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

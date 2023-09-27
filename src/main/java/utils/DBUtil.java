package utils;

import models.Match;
import models.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
        Path path = Path.of("C:\\Users\\Konstantin\\Desktop\\Projects\\TennisScoreboard\\src\\main\\resources\\init.sql");
        try (Session session = sessionFactory.openSession()) {
            List<String> sqlQueries = Files.readAllLines(path);
            session.beginTransaction();
            for (String sqlQuery : sqlQueries) {
                session.createNativeQuery(sqlQuery).executeUpdate();
            }
            session.getTransaction().commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package utils;

import models.Match;
import models.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.*;

public class HibernateRunner {
    private static SessionFactory sessionFactory;
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure().addAnnotatedClass(Player.class).addAnnotatedClass(Match.class);
        sessionFactory = configuration.buildSessionFactory();
        initTables();
    }
    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure().addAnnotatedClass(Player.class).addAnnotatedClass(Match.class);
        sessionFactory = configuration.buildSessionFactory();
        initTables();
        return sessionFactory;
    }

    public static void initTables() {

    }
}

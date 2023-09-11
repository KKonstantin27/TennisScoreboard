//package utils;
//
//import models.Match;
//import models.Player;
//import org.h2.Driver;
//import org.h2.jdbcx.JdbcDataSource;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import java.io.*;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//@WebListener
//public class ContextListener implements ServletContextListener {
//    public void contextInitialized(ServletContextEvent event) {
//        JdbcDataSource dataSource = new JdbcDataSource();
//        dataSource.setURL("jdbc:h2:mem:TennisScoreboard");
//        try (Connection connection = dataSource.getConnection()) {
//            Statement statement = connection.createStatement();
//            String path = "C:\\Users\\Konstantin\\Desktop\\Projects\\TennisScoreboard\\src\\main\\resources\\init.sql";
//            FileReader fileReader = new FileReader(path);
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                statement.execute(line);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
//
//
//

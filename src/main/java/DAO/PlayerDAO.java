package DAO;

import models.Player;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.DBUtil;

import java.util.Optional;

public class PlayerDAO {
    public Player getByName(String name) {
        try (Session session = DBUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Query query = session.createQuery("FROM Player WHERE name = :name");
            query.setParameter("name", name);
            Optional<Player> player = query.uniqueResultOptional();
            session.getTransaction().commit();
            return player.isEmpty() ? save(name) : player.get();
        }
    }
    public Player save(String name) {
        try(Session session = DBUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Player player = new Player(name);
            session.save(player);
            session.getTransaction().commit();
            return player;
        }
    }
}

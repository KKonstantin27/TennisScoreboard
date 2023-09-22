package DAO;

import models.Match;
import models.Player;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.DBUtil;

import java.util.List;

public class MatchDAO {
    public int save(Match match) {
        try(Session session = DBUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            int id = (int) session.save(match);
            session.getTransaction().commit();
            return id;
        }
    }
    public List<Match> getAll() {
        try (Session session = DBUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Match> matchList = session.createQuery("FROM Match").getResultList();
            session.getTransaction().commit();
            return matchList;
        }
    }

    public List<Match> getByPlayer(Player player) {
        try(Session session = DBUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("FROM Match WHERE player1 = :player1 OR player2 = :player2");
            query.setParameter("player1", player);
            query.setParameter("player2", player);
            List<Match> matchList = query.getResultList();
            session.getTransaction().commit();
            return matchList;
        }
    }
}

package services;

import dao.MatchDAO;
import models.Match;
import models.Player;

import java.util.List;

public class FinishedMatchesService {
    private MatchDAO matchDAO = new MatchDAO();

    public int saveFinishedMatch(Match match) {
        return matchDAO.save(match);
    }

    public List<Match> readFinishedMatches() {
        return matchDAO.getAll();
    }

    public List<Match> readFinishedMatch(Player player) {
        return matchDAO.getByPlayer(player);
    }
}

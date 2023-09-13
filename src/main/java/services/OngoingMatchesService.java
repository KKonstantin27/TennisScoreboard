package services;

import models.Match;
import models.MatchScore;
import models.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class OngoingMatchesService {
    private static HashMap<UUID, MatchScore> ongoingMatches = new HashMap<>();

    public String createOngoingMatch(Player player1, Player player2) {
        Match ongoingMatch = new Match(player1, player2);
        return addOngoingMatch(ongoingMatch);
    }
    private String addOngoingMatch(Match ongoingMatch) {
        UUID uuid = UUID.randomUUID();
        ongoingMatches.put(uuid, new MatchScore(ongoingMatch));
        return uuid.toString();
    }

    public MatchScore getOngoingMatch (UUID uuid) {
        return ongoingMatches.get(uuid);
    }
}

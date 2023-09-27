package services;

import models.Match;
import models.MatchScore;
import models.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private static Map<UUID, MatchScore> ongoingMatches = new ConcurrentHashMap<>();

    public String createOngoingMatch(Player player1, Player player2) {
        Match ongoingMatch = new Match(player1, player2);
        return addOngoingMatch(ongoingMatch);
    }

    private String addOngoingMatch(Match ongoingMatch) {
        UUID uuid = UUID.randomUUID();
        ongoingMatches.put(uuid, new MatchScore(ongoingMatch, uuid));
        return uuid.toString();
    }

    public MatchScore getOngoingMatch(UUID uuid) {
        return ongoingMatches.get(uuid);
    }

    public void removeOngoingMatch(UUID uuid) {
        ongoingMatches.remove(uuid);
    }
}

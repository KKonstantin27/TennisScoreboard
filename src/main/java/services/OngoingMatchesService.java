package services;

import models.Match;
import models.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class OngoingMatchesService {
    private HashMap<UUID, Match> ongoingMatches = new HashMap<>();

    public String createOngoingMatch(Player player1, Player player2) {
        Match ongoingMatch = new Match(player1, player2);
        return addOngoingMatch(ongoingMatch);
    }
    private String addOngoingMatch(Match ongoingMatch) {
        UUID uuid = UUID.randomUUID();
        ongoingMatches.put(uuid, ongoingMatch);
        return uuid.toString();
    }

    public Match getOngoingMatch (UUID uuid) {
        return ongoingMatches.get(uuid);
    }
}

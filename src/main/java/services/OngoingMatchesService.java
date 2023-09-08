package services;

import models.Match;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class OngoingMatchesService {
    private HashMap<UUID, Match> ongoingMatches;

    public void addOngoingMatch(Match ongoingMatch, UUID uuid) {
        ongoingMatches.put(uuid, ongoingMatch);
    }

    public Match getOngoingMatch (UUID uuid) {
        return ongoingMatches.get(uuid);
    }
}

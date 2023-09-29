package models;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class MatchScore {
    private Match match;
    private int currentSet;
    private UUID uuid;
    private int p1CurrentScore;
    private int p2CurrentScore;
    private Map<Integer, Integer> p1SetScore;
    private Map<Integer, Integer> p2SetScore;
    private int p1MatchScore;
    private int p2MatchScore;
    private boolean tieBreak;
    private boolean matchEnded;

    public MatchScore(Match match, UUID uuid) {
        this.match = match;
        this.uuid = uuid;
        this.currentSet = 1;
        this.p1CurrentScore = 0;
        this.p2CurrentScore = 0;
        this.p1SetScore = fillStartValues();
        this.p2SetScore = fillStartValues();
        this.p1MatchScore = 0;
        this.p2MatchScore = 0;
        this.tieBreak = false;
        this.matchEnded = false;
    }

    private Map<Integer, Integer> fillStartValues() {
        Map<Integer, Integer> setScore = new HashMap<>();
        for (int i = 1; i <= 3; i++) {
            setScore.put(i, 0);
        }
        return setScore;
    }

    public int getP1CurrentSetScore() {
        return p1SetScore.get(currentSet);
    }

    public int getP2CurrentSetScore() {
        return p2SetScore.get(currentSet);
    }

    public void setP1CurrentSetScore(int p1CurrentSetScore) {
        p1SetScore.put(currentSet, p1CurrentSetScore);
    }

    public void setP2CurrentSetScore(int p2CurrentSetScore) {
        p2SetScore.put(currentSet, p2CurrentSetScore);
    }

    public void setWinner(Player player) {
        match.setWinner(player);
    }

}

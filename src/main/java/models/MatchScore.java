package models;

import lombok.Data;

import java.util.*;

@Data
public class MatchScore {
    private Match match;
    private int currentSet;
    private Map<Integer, Integer> p1Score;
    private Map<Integer, Integer> p2Score;
    private Map<Integer, Integer> p1SetScore;
    private Map<Integer, Integer> p2SetScore;
    private int p1MatchScore;
    private int p2MatchScore;
    private boolean TieBreak;
    private boolean MatchEnded;

    public MatchScore (Match match) {
        this.match = match;
        this.currentSet = 1;
        this.p1Score = fillStartValues();
        this.p2Score = fillStartValues();
        this.p1SetScore = fillStartValues();
        this.p2SetScore = fillStartValues();
        this.p1MatchScore = 0;
        this.p2MatchScore = 0;
        this.TieBreak = false;
        this.MatchEnded = false;
    }

    private Map<Integer, Integer> fillStartValues() {
        Map<Integer, Integer> setScore = new HashMap<>();
        for (int i = 1; i <= 3; i++) {
            setScore.put(i, 0);
        }
        return setScore;
    }
    public int getP1CurrentScore() {
        return p1Score.get(currentSet);
    }

    public int getP2CurrentScore() {
        return p2Score.get(currentSet);
    }
    public int getP1CurrentSetScore() {
        return p1SetScore.get(currentSet);
    }
    public int getP2CurrentSetScore() {
        return p2SetScore.get(currentSet);
    }
    public void setP1CurrentScore(int p1CurrentScore) {
        p1Score.put(currentSet, p1CurrentScore);
    }
    public void setP2CurrentScore(int p2CurrentScore) {
        p2Score.put(currentSet, p2CurrentScore);
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

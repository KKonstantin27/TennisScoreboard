package services;

import models.MatchScore;

public class CalculationService {
    private MatchScore matchScore;
    private int scoringPlayer;

    public MatchScore calculate(int id, MatchScore matchScore) {
        this.matchScore = matchScore;
        defineScoringPlayer(id);
        plusScore();
        if (matchScore.isTieBreak()) {
            if (isTieBreakContinues(matchScore.getP1CurrentScore(), matchScore.getP2CurrentScore()))
                return matchScore;
        } else {
            if (isGameContinues(matchScore.getP1CurrentScore(), matchScore.getP2CurrentScore()))
                return matchScore;
        }
        plusGame();
        if (isSetContinues(matchScore.getP1CurrentSetScore(), matchScore.getP2CurrentSetScore()))
            return matchScore;
        plusSet();
        matchScore.setTieBreak(false);
        if (isMatchEnded(matchScore.getP1MatchScore(), matchScore.getP2MatchScore())) {
            matchScore.setMatchEnded(true);
            setMatchWinner();
        }
        return matchScore;
    }

    private void plusScore() {
        if (scoringPlayer == 1) {
            matchScore.setP1CurrentScore(matchScore.getP1CurrentScore() + 1);
        } else {
            matchScore.setP2CurrentScore(matchScore.getP2CurrentScore() + 1);
        }
    }

    private boolean isGameContinues(int p1CurrentScore, int p2CurrentScore) {
        boolean isGameContinues = true;
        if ((p1CurrentScore == 4 && p2CurrentScore < 3) || (p1CurrentScore < 3 && p2CurrentScore == 4)) {
            isGameContinues = false;
        } else if ((p1CurrentScore > 4 || p2CurrentScore > 4) && (Math.abs(p1CurrentScore - p2CurrentScore)) == 2) {
            isGameContinues = false;
        }
        return isGameContinues;
    }

    private void plusGame() {
        if (scoringPlayer == 1) {
            matchScore.setP1CurrentSetScore(matchScore.getP1CurrentSetScore() + 1);
        } else {
            matchScore.setP2CurrentSetScore(matchScore.getP2CurrentSetScore() + 1);
        }
        resetCurrentScore();
    }

    private boolean isSetContinues(int p1CurrentSetScore, int p2CurrentSetScore) {
        boolean isSetContinues = true;
        if ((p1CurrentSetScore == 6 && p2CurrentSetScore < 5) || (p1CurrentSetScore < 5 && p2CurrentSetScore == 6)) {
            isSetContinues = false;
        } else if (p1CurrentSetScore == 7 || p2CurrentSetScore == 7) {
            isSetContinues = false;
        } else if (p1CurrentSetScore == 6 && p2CurrentSetScore == 6) {
            startTieBreak();
        }
        return isSetContinues;
    }

    private void plusSet() {
        if (scoringPlayer == 1) {
            matchScore.setP1MatchScore(matchScore.getP1MatchScore() + 1);
        } else {
            matchScore.setP2MatchScore(matchScore.getP2MatchScore() + 1);
        }
        matchScore.setCurrentSet(matchScore.getCurrentSet() + 1);
    }

    private boolean isMatchEnded(int p1MatchScore, int p2MatchScore) {
        boolean isMatchEnded = false;
        if (p1MatchScore == 2) {
            isMatchEnded = true;
        } else if (p2MatchScore == 2) {
            isMatchEnded = true;
        }
        return isMatchEnded;
    }

    private void startTieBreak() {
        matchScore.setTieBreak(true);
        resetCurrentScore();
    }

    private boolean isTieBreakContinues(int p1CurrentScore, int p2CurrentScore) {
        boolean isTieBreakContinues = true;
        if ((p1CurrentScore == 7 && p2CurrentScore < 6) || (p1CurrentScore < 6 && p2CurrentScore == 7)) {
            isTieBreakContinues = false;
        } else if ((p1CurrentScore > 7 || p2CurrentScore > 7) && (Math.abs(p1CurrentScore - p2CurrentScore)) == 2) {
            isTieBreakContinues = false;
        }
        return isTieBreakContinues;
    }

    private void resetCurrentScore() {
        matchScore.setP1CurrentScore(0);
        matchScore.setP2CurrentScore(0);
    }

    private void defineScoringPlayer(int id) {
        scoringPlayer = id == matchScore.getMatch().getPlayer1().getId() ? 1 : 2;
    }

    private void setMatchWinner() {
        if (scoringPlayer == 1) {
            matchScore.setWinner(matchScore.getMatch().getPlayer1());
        } else {
            matchScore.setWinner(matchScore.getMatch().getPlayer2());
        }
    }
}

package utils;

import DTO.MatchDTO;
import DTO.MatchScoreDTO;
import models.Match;
import models.MatchScore;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public MatchScoreDTO convertToDTO(MatchScore matchScore) {
        MatchScoreDTO matchScoreDTO = new MatchScoreDTO(matchScore.getMatch(), matchScore.getUuid(),
                String.valueOf(matchScore.getP1CurrentScore()), String.valueOf(matchScore.getP2CurrentScore()),
                matchScore.getP1SetScore(), matchScore.getP2SetScore());
        if (!matchScore.isTieBreak()) {
            matchScoreDTO.setP1CurrentScore(convertScore(matchScore.getP1CurrentScore(), matchScore.getP2CurrentScore()));
            matchScoreDTO.setP2CurrentScore(convertScore(matchScore.getP2CurrentScore(), matchScore.getP1CurrentScore()));
        }
        return matchScoreDTO;
    }

    public List<MatchDTO> convertToDTO(List<Match> matches) {
        List<MatchDTO> matchesDTO = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            matchesDTO.add(new MatchDTO(matches.get(i).getId(), matches.get(i).getPlayer1(),
                    matches.get(i).getPlayer2(), matches.get(i).getWinner()));
        }
        return matchesDTO;
    }

    private String convertScore(int p1CurrentScore, int p2CurrentScore) {
        String convertedPlayerScore = "";
        if (p1CurrentScore < 3) {
            convertedPlayerScore = String.format("%02d", p1CurrentScore * 15);
        } else if (p1CurrentScore > 3 && p1CurrentScore > p2CurrentScore) {
            convertedPlayerScore = "AD";
        } else if (p1CurrentScore >= 3 && p1CurrentScore < p2CurrentScore) {
            convertedPlayerScore = "";
        } else if ((p1CurrentScore == 3) || (p1CurrentScore > 3 && p1CurrentScore == p2CurrentScore)) {
            convertedPlayerScore = "40";
        }
        return convertedPlayerScore;
    }
}

package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.Match;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Data
public class MatchScoreDTO {
    private Match match;
    private UUID uuid;
    private String p1CurrentScore;
    private String p2CurrentScore;
    private Map<Integer, Integer> p1SetScore;
    private Map<Integer, Integer> p2SetScore;
    private boolean tieBreak;
}

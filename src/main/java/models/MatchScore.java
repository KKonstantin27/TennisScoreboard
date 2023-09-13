package models;

import lombok.Data;

@Data
public class MatchScore {
    private Match match;
    private int p1Score;
    private int p2Score;
    private int p1Set1;
    private int p2Set1;
    private int p1Set2;
    private int p2Set2;
    private int p1Set3;
    private int p2Set3;

    public MatchScore (Match match) {
        this.match = match;
        this.p1Score = 0;
        this.p2Score = 0;
        this.p1Set1 = 0;
        this.p2Set1 = 0;
        this.p1Set2 = 0;
        this.p2Set2 = 0;
        this.p1Set3 = 0;
        this.p2Set3 = 0;
    }
}

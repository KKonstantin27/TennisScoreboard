package models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Players")
public class Player {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Name")
    private String name;

    @OneToMany(mappedBy = "player1")
    private List<Match> matchesAsPlayer1;

    @OneToMany(mappedBy = "player2")
    private List<Match> matchesAsPlayer2;

    @OneToMany(mappedBy = "winner")
    private List<Match> matchesAsWinner;

    public Player() {

    }

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Match> getMatchesAsPlayer1() {
        return matchesAsPlayer1;
    }

    public void setMatchesAsPlayer1(List<Match> matchesAsPlayer1) {
        this.matchesAsPlayer1 = matchesAsPlayer1;
    }

    public List<Match> getMatchesAsPlayer2() {
        return matchesAsPlayer2;
    }

    public void setMatchesAsPlayer2(List<Match> matchesAsPlayer2) {
        this.matchesAsPlayer2 = matchesAsPlayer2;
    }

    public List<Match> getMatchesAsWinner() {
        return matchesAsWinner;
    }

    public void setMatchesAsWinner(List<Match> matchesAsWinner) {
        this.matchesAsWinner = matchesAsWinner;
    }
}

package models;

import jakarta.persistence.*;

@Entity
@Table(name = "Matches")
public class Match {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "Player1", referencedColumnName = "ID")
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "Player2", referencedColumnName = "ID")
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "Winner", referencedColumnName = "ID")
    private Player winner;

    public Match() {

    }
    public Match(int id, Player player1, Player player2, Player winner) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}

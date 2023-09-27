package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import models.Player;

@Data
@AllArgsConstructor
public class MatchDTO {
    private int id;
    private Player player1;
    private Player player2;
    private Player winner;
}

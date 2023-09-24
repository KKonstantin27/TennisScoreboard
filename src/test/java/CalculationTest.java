import DAO.PlayerDAO;
import models.Match;
import models.MatchScore;
import models.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import services.CalculationService;

import java.util.UUID;

public class CalculationTest {
    private static PlayerDAO playerDAO = new PlayerDAO();
    private static CalculationService calculationService;
    private static Player player1;
    private static Player player2;
    private MatchScore matchScore;
    @BeforeClass
    public static void createPlayersCalculationService() {
        calculationService = new CalculationService();
        player1 = playerDAO.getByName("Рафаэль Надаль").get();
        player2 = playerDAO.getByName("Роджер Федерер").get();
    }
    @Before
    public void initMatchScore(){
        Match match = new Match(player1, player2);
        UUID uuid = UUID.randomUUID();
        matchScore = new MatchScore(match, uuid);
    }

    @Test
    public void p1PlusScore40_40() {
        matchScore.setP1CurrentScore(3);
        matchScore.setP2CurrentScore(3);
        Assert.assertEquals(0, calculationService.calculate(1, matchScore, matchScore.getUuid()).getP1CurrentSetScore());
    }
    @Test
    public void p1PlusScore40_0() {
        matchScore.setP1CurrentScore(3);
        matchScore.setP2MatchScore(0);
        Assert.assertEquals(1, calculationService.calculate(1, matchScore, matchScore.getUuid()).getP1CurrentSetScore());
    }
    @Test
    public void tieBreakStart6_6() {

    }
}

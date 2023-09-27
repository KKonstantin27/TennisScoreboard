import DAO.PlayerDAO;
import models.Match;
import models.MatchScore;
import models.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import services.CalculationService;

import java.util.HashMap;
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
        playerDAO.save("РОДЖЕР ФЕДЕРЕР");
        playerDAO.save("РАФАЭЛЬ НАДАЛЬ");
        player1 = playerDAO.getByName("РОДЖЕР ФЕДЕРЕР").get();
        player2 = playerDAO.getByName("РАФАЭЛЬ НАДАЛЬ").get();
    }

    @Before
    public void initMatchScore(){
        Match match = new Match(player1, player2);
        UUID uuid = UUID.randomUUID();
        matchScore = new MatchScore(match, uuid);
    }

    @Test
    public void testP1PlusSetScoreGame_0() {
        matchScore.setP1CurrentScore(3);
        matchScore.setP2CurrentScore(0);
        calculationService.calculate(1, matchScore, matchScore.getUuid());
        Assert.assertEquals(1, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP2PlusSetScore0_Game() {
        matchScore.setP1CurrentScore(0);
        matchScore.setP2CurrentScore(3);
        calculationService.calculate(2, matchScore, matchScore.getUuid());
        Assert.assertEquals(1, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }
    @Test
    public void testP1PlusSetScoreGame_30() {
        matchScore.setP1CurrentScore(3);
        matchScore.setP2CurrentScore(2);
        calculationService.calculate(1, matchScore, matchScore.getUuid());
        Assert.assertEquals(1, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP2PlusSetScore30_Game() {
        matchScore.setP1CurrentScore(2);
        matchScore.setP2CurrentScore(3);
        calculationService.calculate(2, matchScore, matchScore.getUuid());
        Assert.assertEquals(1, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP1DeuceContinuesAD_40() {
        matchScore.setP1CurrentScore(3);
        matchScore.setP2CurrentScore(3);
        calculationService.calculate(1, matchScore, matchScore.getUuid());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(4, matchScore.getP1CurrentScore());
        Assert.assertEquals(3, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP2DeuceContinues40_AD() {
        matchScore.setP1CurrentScore(3);
        matchScore.setP2CurrentScore(3);
        calculationService.calculate(2, matchScore, matchScore.getUuid());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(3, matchScore.getP1CurrentScore());
        Assert.assertEquals(4, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP1DeuceFinishedGame_40() {
        matchScore.setP1CurrentScore(4);
        matchScore.setP2CurrentScore(3);
        calculationService.calculate(1, matchScore, matchScore.getUuid());
        Assert.assertEquals(1, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP2DeuceFinished40_Game() {
        matchScore.setP1CurrentScore(3);
        matchScore.setP2CurrentScore(4);
        calculationService.calculate(2, matchScore, matchScore.getUuid());
        Assert.assertEquals(1, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP1PlusMatchScore6_0() {
        matchScore.setP1CurrentScore(3);
        matchScore.setP2CurrentScore(0);
        matchScore.setP1CurrentSetScore(5);
        matchScore.setP2CurrentSetScore(0);
        calculationService.calculate(1, matchScore, matchScore.getUuid());
        Assert.assertEquals(1, matchScore.getP1MatchScore());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP2PlusMatchScore0_6() {
        matchScore.setP1CurrentScore(0);
        matchScore.setP2CurrentScore(3);
        matchScore.setP1CurrentSetScore(0);
        matchScore.setP2CurrentSetScore(5);
        calculationService.calculate(2, matchScore, matchScore.getUuid());
        Assert.assertEquals(1, matchScore.getP2MatchScore());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP1PlusMatchScore6_4() {
        matchScore.setP1CurrentScore(3);
        matchScore.setP2CurrentScore(0);
        matchScore.setP1CurrentSetScore(5);
        matchScore.setP2CurrentSetScore(4);
        calculationService.calculate(1, matchScore, matchScore.getUuid());
        Assert.assertEquals(1, matchScore.getP1MatchScore());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP2PlusMatchScore4_6() {
        matchScore.setP1CurrentScore(0);
        matchScore.setP2CurrentScore(3);
        matchScore.setP1CurrentSetScore(4);
        matchScore.setP2CurrentSetScore(5);
        calculationService.calculate(2, matchScore, matchScore.getUuid());
        Assert.assertEquals(1, matchScore.getP2MatchScore());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }


    @Test
    public void testP1PlusMatchScore7_5() {
        matchScore.setP1CurrentScore(3);
        matchScore.setP2CurrentScore(0);
        matchScore.setP1CurrentSetScore(6);
        matchScore.setP2CurrentSetScore(5);
        calculationService.calculate(1, matchScore, matchScore.getUuid());
        Assert.assertEquals(1, matchScore.getP1MatchScore());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP2PlusMatchScore5_7() {
        matchScore.setP1CurrentScore(0);
        matchScore.setP2CurrentScore(3);
        matchScore.setP1CurrentSetScore(5);
        matchScore.setP2CurrentSetScore(6);
        calculationService.calculate(2, matchScore, matchScore.getUuid());
        Assert.assertEquals(1, matchScore.getP2MatchScore());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP1TieBreakStarted6_6() {
        matchScore.setP1CurrentScore(3);
        matchScore.setP2CurrentScore(0);
        matchScore.setP1CurrentSetScore(5);
        matchScore.setP2CurrentSetScore(6);
        calculationService.calculate(1, matchScore, matchScore.getUuid());
        Assert.assertTrue(matchScore.isTieBreak());
        Assert.assertEquals(0, matchScore.getP1MatchScore());
        Assert.assertEquals(1, matchScore.getCurrentSet());
        Assert.assertEquals(6, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(6, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP2TieBreakStarted6_6() {
        matchScore.setP1CurrentScore(0);
        matchScore.setP2CurrentScore(3);
        matchScore.setP1CurrentSetScore(6);
        matchScore.setP2CurrentSetScore(5);
        calculationService.calculate(2, matchScore, matchScore.getUuid());
        Assert.assertTrue(matchScore.isTieBreak());
        Assert.assertEquals(0, matchScore.getP2MatchScore());
        Assert.assertEquals(1, matchScore.getCurrentSet());
        Assert.assertEquals(6, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(6, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP1TieBreakFinished7_0() {
        matchScore.setP1CurrentScore(6);
        matchScore.setP2CurrentScore(0);
        matchScore.setTieBreak(true);
        matchScore.setP1CurrentSetScore(6);
        matchScore.setP2CurrentSetScore(6);
        calculationService.calculate(1, matchScore, matchScore.getUuid());
        Assert.assertEquals(1, matchScore.getP1MatchScore());
        Assert.assertFalse(matchScore.isTieBreak());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP2TieBreakFinished0_7() {
        matchScore.setP1CurrentScore(0);
        matchScore.setP2CurrentScore(6);
        matchScore.setTieBreak(true);
        matchScore.setP1CurrentSetScore(6);
        matchScore.setP2CurrentSetScore(6);
        calculationService.calculate(2, matchScore, matchScore.getUuid());
        Assert.assertEquals(1, matchScore.getP2MatchScore());
        Assert.assertFalse(matchScore.isTieBreak());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP1TieBreakContinues7_6() {
        matchScore.setP1CurrentScore(6);
        matchScore.setP2CurrentScore(6);
        matchScore.setTieBreak(true);
        matchScore.setP1CurrentSetScore(6);
        matchScore.setP2CurrentSetScore(6);
        calculationService.calculate(1, matchScore, matchScore.getUuid());
        Assert.assertTrue(matchScore.isTieBreak());
        Assert.assertEquals(0, matchScore.getP1MatchScore());
        Assert.assertEquals(1, matchScore.getCurrentSet());
        Assert.assertEquals(6, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(6, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(7, matchScore.getP1CurrentScore());
        Assert.assertEquals(6, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP2TieBreakContinues6_7() {
        matchScore.setP1CurrentScore(6);
        matchScore.setP2CurrentScore(6);
        matchScore.setTieBreak(true);
        matchScore.setP1CurrentSetScore(6);
        matchScore.setP2CurrentSetScore(6);
        calculationService.calculate(2, matchScore, matchScore.getUuid());
        Assert.assertTrue(matchScore.isTieBreak());
        Assert.assertEquals(0, matchScore.getP2MatchScore());
        Assert.assertEquals(1, matchScore.getCurrentSet());
        Assert.assertEquals(6, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(6, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(6, matchScore.getP1CurrentScore());
        Assert.assertEquals(7, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP1TieBreakFinished8_6() {
        matchScore.setP1CurrentScore(7);
        matchScore.setP2CurrentScore(6);
        matchScore.setTieBreak(true);
        matchScore.setP1CurrentSetScore(6);
        matchScore.setP2CurrentSetScore(6);
        calculationService.calculate(1, matchScore, matchScore.getUuid());
        Assert.assertEquals(1, matchScore.getP1MatchScore());
        Assert.assertFalse(matchScore.isTieBreak());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP2TieBreakFinished6_8() {
        matchScore.setP1CurrentScore(6);
        matchScore.setP2CurrentScore(7);
        matchScore.setTieBreak(true);
        matchScore.setP1CurrentSetScore(6);
        matchScore.setP2CurrentSetScore(6);
        calculationService.calculate(2, matchScore, matchScore.getUuid());
        Assert.assertEquals(1, matchScore.getP2MatchScore());
        Assert.assertFalse(matchScore.isTieBreak());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void testP1FinishedMatch2_0() {
        matchScore.setCurrentSet(2);
        matchScore.setP1MatchScore(1);
        matchScore.setP1CurrentScore(3);
        matchScore.setP2CurrentScore(0);
        matchScore.setP1CurrentSetScore(5);
        matchScore.setP2CurrentSetScore(0);
        calculationService.calculate(1, matchScore, matchScore.getUuid());
        Assert.assertTrue(matchScore.isMatchEnded());
        Assert.assertEquals(matchScore.getMatch().getWinner(), matchScore.getMatch().getPlayer1());
    }

    @Test
    public void testP2FinishedMatch0_2() {
        matchScore.setCurrentSet(2);
        matchScore.setP2MatchScore(1);
        matchScore.setP1CurrentScore(0);
        matchScore.setP2CurrentScore(3);
        matchScore.setP1CurrentSetScore(0);
        matchScore.setP2CurrentSetScore(5);
        calculationService.calculate(2, matchScore, matchScore.getUuid());
        Assert.assertTrue(matchScore.isMatchEnded());
        Assert.assertEquals(matchScore.getMatch().getWinner(), matchScore.getMatch().getPlayer2());
    }
}

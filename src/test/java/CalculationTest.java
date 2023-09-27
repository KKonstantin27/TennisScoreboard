import DAO.PlayerDAO;
import models.Match;
import models.MatchScore;
import models.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import services.CalculationService;

import java.util.Optional;
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
        Optional<Player> player1Opt = playerDAO.getByName("РОДЖЕР ФЕДЕРЕР");
        Optional<Player> player2Opt = playerDAO.getByName("РАФАЭЛЬ НАДАЛЬ");
        player1 = player1Opt.isEmpty() ? playerDAO.save("РОДЖЕР ФЕДЕРЕР") : player1Opt.get();
        player2 = player2Opt.isEmpty() ? playerDAO.save("РАФАЭЛЬ НАДАЛЬ") : player2Opt.get();
    }

    @Before
    public void initMatchScore() {
        Match match = new Match(player1, player2);
        UUID uuid = UUID.randomUUID();
        matchScore = new MatchScore(match, uuid);
    }

    @Test
    public void scoringP1PlusSetScoreGame_0() {
        matchScore.setP1CurrentScore(3);
        calculationService.calculate(1, matchScore);
        Assert.assertEquals(1, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP2PlusSetScore0_Game() {
        matchScore.setP2CurrentScore(3);
        calculationService.calculate(2, matchScore);
        Assert.assertEquals(1, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP1PlusSetScoreGame_30() {
        matchScore.setP1CurrentScore(3);
        matchScore.setP2CurrentScore(2);
        calculationService.calculate(1, matchScore);
        Assert.assertEquals(1, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP2PlusSetScore30_Game() {
        matchScore.setP1CurrentScore(2);
        matchScore.setP2CurrentScore(3);
        calculationService.calculate(2, matchScore);
        Assert.assertEquals(1, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP1DeuceContinuesAD_40() {
        matchScore.setP1CurrentScore(3);
        matchScore.setP2CurrentScore(3);
        calculationService.calculate(1, matchScore);
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(4, matchScore.getP1CurrentScore());
        Assert.assertEquals(3, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP2DeuceContinues40_AD() {
        matchScore.setP1CurrentScore(3);
        matchScore.setP2CurrentScore(3);
        calculationService.calculate(2, matchScore);
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(3, matchScore.getP1CurrentScore());
        Assert.assertEquals(4, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP1DeuceFinishedGame_40() {
        matchScore.setP1CurrentScore(4);
        matchScore.setP2CurrentScore(3);
        calculationService.calculate(1, matchScore);
        Assert.assertEquals(1, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP2DeuceFinished40_Game() {
        matchScore.setP1CurrentScore(3);
        matchScore.setP2CurrentScore(4);
        calculationService.calculate(2, matchScore);
        Assert.assertEquals(1, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP1PlusMatchScore6_0() {
        matchScore.setP1CurrentScore(3);
        matchScore.setP1CurrentSetScore(5);
        calculationService.calculate(1, matchScore);
        Assert.assertEquals(1, matchScore.getP1MatchScore());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP2PlusMatchScore0_6() {
        matchScore.setP2CurrentScore(3);
        matchScore.setP2CurrentSetScore(5);
        calculationService.calculate(2, matchScore);
        Assert.assertEquals(1, matchScore.getP2MatchScore());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP1PlusMatchScore6_4() {
        matchScore.setP1CurrentScore(3);
        matchScore.setP1CurrentSetScore(5);
        matchScore.setP2CurrentSetScore(4);
        calculationService.calculate(1, matchScore);
        Assert.assertEquals(1, matchScore.getP1MatchScore());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP2PlusMatchScore4_6() {
        matchScore.setP2CurrentScore(3);
        matchScore.setP1CurrentSetScore(4);
        matchScore.setP2CurrentSetScore(5);
        calculationService.calculate(2, matchScore);
        Assert.assertEquals(1, matchScore.getP2MatchScore());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }


    @Test
    public void scoringP1PlusMatchScore7_5() {
        matchScore.setP1CurrentScore(3);
        matchScore.setP1CurrentSetScore(6);
        matchScore.setP2CurrentSetScore(5);
        calculationService.calculate(1, matchScore);
        Assert.assertEquals(1, matchScore.getP1MatchScore());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP2PlusMatchScore5_7() {
        matchScore.setP2CurrentScore(3);
        matchScore.setP1CurrentSetScore(5);
        matchScore.setP2CurrentSetScore(6);
        calculationService.calculate(2, matchScore);
        Assert.assertEquals(1, matchScore.getP2MatchScore());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP1TieBreakStarted6_6() {
        matchScore.setP1CurrentScore(3);
        matchScore.setP1CurrentSetScore(5);
        matchScore.setP2CurrentSetScore(6);
        calculationService.calculate(1, matchScore);
        Assert.assertTrue(matchScore.isTieBreak());
        Assert.assertEquals(0, matchScore.getP1MatchScore());
        Assert.assertEquals(1, matchScore.getCurrentSet());
        Assert.assertEquals(6, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(6, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP2TieBreakStarted6_6() {
        matchScore.setP2CurrentScore(3);
        matchScore.setP1CurrentSetScore(6);
        matchScore.setP2CurrentSetScore(5);
        calculationService.calculate(2, matchScore);
        Assert.assertTrue(matchScore.isTieBreak());
        Assert.assertEquals(0, matchScore.getP2MatchScore());
        Assert.assertEquals(1, matchScore.getCurrentSet());
        Assert.assertEquals(6, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(6, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP1TieBreakFinished7_0() {
        matchScore.setP1CurrentScore(6);
        matchScore.setTieBreak(true);
        matchScore.setP1CurrentSetScore(6);
        matchScore.setP2CurrentSetScore(6);
        calculationService.calculate(1, matchScore);
        Assert.assertEquals(1, matchScore.getP1MatchScore());
        Assert.assertFalse(matchScore.isTieBreak());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP2TieBreakFinished0_7() {
        matchScore.setP2CurrentScore(6);
        matchScore.setTieBreak(true);
        matchScore.setP1CurrentSetScore(6);
        matchScore.setP2CurrentSetScore(6);
        calculationService.calculate(2, matchScore);
        Assert.assertEquals(1, matchScore.getP2MatchScore());
        Assert.assertFalse(matchScore.isTieBreak());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP1TieBreakContinues7_6() {
        matchScore.setP1CurrentScore(6);
        matchScore.setP2CurrentScore(6);
        matchScore.setTieBreak(true);
        matchScore.setP1CurrentSetScore(6);
        matchScore.setP2CurrentSetScore(6);
        calculationService.calculate(1, matchScore);
        Assert.assertTrue(matchScore.isTieBreak());
        Assert.assertEquals(0, matchScore.getP1MatchScore());
        Assert.assertEquals(1, matchScore.getCurrentSet());
        Assert.assertEquals(6, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(6, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(7, matchScore.getP1CurrentScore());
        Assert.assertEquals(6, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP2TieBreakContinues6_7() {
        matchScore.setP1CurrentScore(6);
        matchScore.setP2CurrentScore(6);
        matchScore.setTieBreak(true);
        matchScore.setP1CurrentSetScore(6);
        matchScore.setP2CurrentSetScore(6);
        calculationService.calculate(2, matchScore);
        Assert.assertTrue(matchScore.isTieBreak());
        Assert.assertEquals(0, matchScore.getP2MatchScore());
        Assert.assertEquals(1, matchScore.getCurrentSet());
        Assert.assertEquals(6, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(6, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(6, matchScore.getP1CurrentScore());
        Assert.assertEquals(7, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP1TieBreakFinished8_6() {
        matchScore.setP1CurrentScore(7);
        matchScore.setP2CurrentScore(6);
        matchScore.setTieBreak(true);
        matchScore.setP1CurrentSetScore(6);
        matchScore.setP2CurrentSetScore(6);
        calculationService.calculate(1, matchScore);
        Assert.assertEquals(1, matchScore.getP1MatchScore());
        Assert.assertFalse(matchScore.isTieBreak());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP2TieBreakFinished6_8() {
        matchScore.setP1CurrentScore(6);
        matchScore.setP2CurrentScore(7);
        matchScore.setTieBreak(true);
        matchScore.setP1CurrentSetScore(6);
        matchScore.setP2CurrentSetScore(6);
        calculationService.calculate(2, matchScore);
        Assert.assertEquals(1, matchScore.getP2MatchScore());
        Assert.assertFalse(matchScore.isTieBreak());
        Assert.assertEquals(2, matchScore.getCurrentSet());
        Assert.assertEquals(0, matchScore.getP1CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP2CurrentSetScore());
        Assert.assertEquals(0, matchScore.getP1CurrentScore());
        Assert.assertEquals(0, matchScore.getP2CurrentScore());
    }

    @Test
    public void scoringP1MatchFinished2_0() {
        matchScore.setCurrentSet(2);
        matchScore.setP1MatchScore(1);
        matchScore.setP1CurrentScore(3);
        matchScore.setP1CurrentSetScore(5);
        calculationService.calculate(1, matchScore);
        Assert.assertTrue(matchScore.isMatchEnded());
        Assert.assertEquals(matchScore.getMatch().getWinner(), matchScore.getMatch().getPlayer1());
    }

    @Test
    public void scoringP2MatchFinished0_2() {
        matchScore.setCurrentSet(2);
        matchScore.setP2MatchScore(1);
        matchScore.setP2CurrentScore(3);
        matchScore.setP2CurrentSetScore(5);
        calculationService.calculate(2, matchScore);
        Assert.assertTrue(matchScore.isMatchEnded());
        Assert.assertEquals(matchScore.getMatch().getWinner(), matchScore.getMatch().getPlayer2());
    }

    @Test
    public void scoringP1MatchContinues1_1() {
        matchScore.setCurrentSet(3);
        matchScore.setP2MatchScore(1);
        matchScore.setP1CurrentScore(3);
        matchScore.setP1CurrentSetScore(5);
        calculationService.calculate(1, matchScore);
        Assert.assertFalse(matchScore.isMatchEnded());
        Assert.assertEquals(1, matchScore.getP1MatchScore());
        Assert.assertNull(matchScore.getMatch().getWinner());
    }

    @Test
    public void scoringP2MatchContinues1_1() {
        matchScore.setCurrentSet(3);
        matchScore.setP1MatchScore(1);
        matchScore.setP2CurrentScore(3);
        matchScore.setP2CurrentSetScore(5);
        calculationService.calculate(2, matchScore);
        Assert.assertFalse(matchScore.isMatchEnded());
        Assert.assertEquals(1, matchScore.getP2MatchScore());
        Assert.assertNull(matchScore.getMatch().getWinner());
    }

    @Test
    public void scoringP1MatchFinished2_1() {
        matchScore.setCurrentSet(3);
        matchScore.setP1MatchScore(1);
        matchScore.setP2MatchScore(1);
        matchScore.setP1CurrentScore(3);
        matchScore.setP1CurrentSetScore(5);
        calculationService.calculate(1, matchScore);
        Assert.assertTrue(matchScore.isMatchEnded());
        Assert.assertEquals(matchScore.getMatch().getWinner(), matchScore.getMatch().getPlayer1());
    }

    @Test
    public void scoringP2MatchFinished1_2() {
        matchScore.setCurrentSet(3);
        matchScore.setP1MatchScore(1);
        matchScore.setP2MatchScore(1);
        matchScore.setP2CurrentScore(3);
        matchScore.setP2CurrentSetScore(5);
        calculationService.calculate(2, matchScore);
        Assert.assertTrue(matchScore.isMatchEnded());
        Assert.assertEquals(matchScore.getMatch().getWinner(), matchScore.getMatch().getPlayer2());
    }
}

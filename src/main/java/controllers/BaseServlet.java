package controllers;

import DAO.PlayerDAO;
import DTO.MatchScoreDTO;
import services.CalculationService;
import services.FinishedMatchesService;
import services.OngoingMatchesService;
import utils.Mapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;


public class BaseServlet extends HttpServlet {
    protected OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    protected CalculationService calculationService = new CalculationService();
    protected PlayerDAO playerDAO = new PlayerDAO();
    protected FinishedMatchesService finishedMatchesService = new FinishedMatchesService();
    protected Mapper mapper = new Mapper();

    protected void configUTF(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    }
    protected void setMatchAttributes(HttpServletRequest request, HttpServletResponse response, MatchScoreDTO matchScoreDTO) {
        request.setAttribute("P1Name", matchScoreDTO.getMatch().getPlayer1().getName());
        request.setAttribute("P2Name", matchScoreDTO.getMatch().getPlayer2().getName());
        request.setAttribute("P1ID", matchScoreDTO.getMatch().getPlayer1().getId());
        request.setAttribute("P2ID", matchScoreDTO.getMatch().getPlayer2().getId());
        request.setAttribute("P1Score", matchScoreDTO.getP1CurrentScore());
        request.setAttribute("P2Score", matchScoreDTO.getP2CurrentScore());
        request.setAttribute("P1Set1Score", matchScoreDTO.getP1SetScore().get(1));
        request.setAttribute("P2Set1Score", matchScoreDTO.getP2SetScore().get(1));
        request.setAttribute("P1Set2Score", matchScoreDTO.getP1SetScore().get(2));
        request.setAttribute("P2Set2Score", matchScoreDTO.getP2SetScore().get(2));
        request.setAttribute("P1Set3Score", matchScoreDTO.getP1SetScore().get(3));
        request.setAttribute("P2Set3Score", matchScoreDTO.getP2SetScore().get(3));
        request.setAttribute("MatchUUID", matchScoreDTO.getUuid());
    }
    protected void setFinishedMatchAttributes(HttpServletRequest request, HttpServletResponse response, MatchScoreDTO matchScoreDTO, int matchID) {
        boolean isPlayer1Winner = matchScoreDTO.getMatch().getWinner().getId() == matchScoreDTO.getMatch().getPlayer1().getId();
        request.setAttribute("MatchID", matchID);
        request.setAttribute("IsPlayer1Winner", isPlayer1Winner);
    }
}


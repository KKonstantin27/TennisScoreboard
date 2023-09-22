package controllers;

import DAO.PlayerDAO;
import DTO.MatchScoreDTO;
import models.Match;
import models.Page;
import services.CalculationService;
import services.FinishedMatchesService;
import services.OngoingMatchesService;
import utils.DBUtil;
import utils.Mapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;


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
        request.setAttribute("ongoingMatch", matchScoreDTO);
        request.setAttribute("p1SetScores", matchScoreDTO.getP1SetScore());
        request.setAttribute("p2SetScores", matchScoreDTO.getP2SetScore());
    }
    protected void setFinishedMatchAttributes(HttpServletRequest request, HttpServletResponse response, MatchScoreDTO matchScoreDTO, int matchID) {
        boolean isPlayer1Winner = matchScoreDTO.getMatch().getWinner().getId() == matchScoreDTO.getMatch().getPlayer1().getId();
        request.setAttribute("MatchID", matchID);
        request.setAttribute("IsPlayer1Winner", isPlayer1Winner);
    }
    protected void setMatchListAttributes(HttpServletRequest request, HttpServletResponse response, List<Match> currentPageMatches, Page page) {
        request.setAttribute("page", page.getCurrentPage());
        request.setAttribute("lastPage", page.getTotalPages());
        request.setAttribute("matches", currentPageMatches);
    }
}


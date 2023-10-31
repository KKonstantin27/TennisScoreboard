package controllers;

import DAO.PlayerDAO;
import DTO.MatchDTO;
import DTO.MatchScoreDTO;
import models.Page;
import services.CalculationService;
import services.FinishedMatchesService;
import services.OngoingMatchesService;
import utils.Mapper;
import utils.Validator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;


public class BaseServlet extends HttpServlet {
    protected OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    protected CalculationService calculationService = new CalculationService();
    protected FinishedMatchesService finishedMatchesService = new FinishedMatchesService();
    protected PlayerDAO playerDAO = new PlayerDAO();
    protected Mapper mapper = new Mapper();
    protected Validator validator = new Validator();

    protected void setMatchAttributes(HttpServletRequest request, HttpServletResponse response, MatchScoreDTO matchScoreDTO) {
        request.setAttribute("ongoingMatch", matchScoreDTO);
        request.setAttribute("p1SetScores", matchScoreDTO.getP1SetScore());
        request.setAttribute("p2SetScores", matchScoreDTO.getP2SetScore());
    }

    protected void setMatchListAttributes(HttpServletRequest request, HttpServletResponse response, List<MatchDTO> currentPageMatchesDTO, Page page) {
        request.setAttribute("page", page.getCurrentPage());
        request.setAttribute("lastPage", page.getTotalPages());
        request.setAttribute("matches", currentPageMatchesDTO);
    }
}


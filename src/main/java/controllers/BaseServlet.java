package controllers;


import dao.PlayerDAO;
import dto.MatchDTO;
import dto.MatchScoreDTO;
import exceptions.InvalidNameFormatForCreationException;
import exceptions.InvalidNameFormatForSearchException;
import exceptions.PlayerDoesNotExistException;
import exceptions.SamePlayersException;
import models.Page;
import services.CalculationService;
import services.FinishedMatchesService;
import services.OngoingMatchesService;
import utils.Mapper;
import utils.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class BaseServlet extends HttpServlet {
    protected OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    protected CalculationService calculationService = new CalculationService();
    protected FinishedMatchesService finishedMatchesService = new FinishedMatchesService();
    protected PlayerDAO playerDAO = new PlayerDAO();
    protected Mapper mapper = new Mapper();
    protected Validator validator = new Validator();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            super.service(request, response);
        } catch (InvalidNameFormatForCreationException | SamePlayersException e) {
            response.setStatus(400);
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/newMatch.jsp");
            dispatcher.forward(request, response);
        } catch (PlayerDoesNotExistException e) {
            response.setStatus(404);
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/matchList.jsp");
            dispatcher.forward(request, response);
        } catch (InvalidNameFormatForSearchException e) {
            response.setStatus(400);
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/matchList.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

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


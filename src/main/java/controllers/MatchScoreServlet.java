package controllers;

import models.MatchScore;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "MatchScoreServlet", value = "/match-score")
public class MatchScoreServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("matchScore.jsp");
        MatchScore matchScore = ongoingMatchesService.getOngoingMatch(UUID.fromString(request.getParameter("uuid")));
        request.setAttribute("MatchScore", matchScore);
        request.setAttribute("Player1", matchScore.getMatch().getPlayer1().getName());
        request.setAttribute("Player2", matchScore.getMatch().getPlayer2().getName());
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

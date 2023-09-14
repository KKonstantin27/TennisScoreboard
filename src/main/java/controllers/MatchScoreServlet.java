package controllers;

import models.MatchScore;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@WebServlet(name = "MatchScoreServlet", value = "/match-score")
public class MatchScoreServlet extends BaseServlet {
    UUID uuid;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("matchScore.jsp");
        uuid = UUID.fromString(request.getParameter("uuid"));
        MatchScore matchScore = ongoingMatchesService.getOngoingMatch(uuid);
        request.setAttribute("MatchScore", matchScore);
        request.setAttribute("Player1", matchScore.getMatch().getPlayer1());
        request.setAttribute("Player2", matchScore.getMatch().getPlayer2());
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("ID"));

        MatchScore matchScore = ongoingMatchesService.getOngoingMatch(uuid);
        calculationService.calculate(id, matchScore, uuid);
        if (matchScore.isMatchEnded()) {
            PrintWriter pw = response.getWriter();
            pw.println("Match ended");
        } else {
            response.sendRedirect("/TennisScoreboard_war_exploded/match-score" + "?uuid=" + URLEncoder.encode(uuid.toString(), StandardCharsets.UTF_8));
        }
    }
}

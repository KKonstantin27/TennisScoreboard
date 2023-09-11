package controllers;

import models.Match;
import models.Player;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "NewMatchServlet", value = "/new-match")
public class NewMatchServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String player1Name = request.getParameter("first-player-name");
        String player2Name = request.getParameter("second-player-name");
        Player player1 = playerDAO.getByName(player1Name);
        Player player2 = playerDAO.getByName(player2Name);
//        Match ongoingMatch = new Match(player1, player2);
//        ongoingMatchesService.addOngoingMatch(ongoingMatch, ongoingMatch.getUuid());
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/match-score?uuid=$" + ongoingMatch.getUuid().toString());
//        dispatcher.forward(request, response);
    }
}

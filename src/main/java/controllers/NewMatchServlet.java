package controllers;

import exceptions.InvalidNameFormatForCreationException;
import exceptions.SamePlayersException;
import models.Player;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;


@WebServlet(name = "NewMatchServlet", value = "/new-match")
public class NewMatchServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/newMatch.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String player1Name = request.getParameter("first-player-name");
        String player2Name = request.getParameter("second-player-name");
        if (!validator.isValidUserInput(player1Name) || !validator.isValidUserInput(player2Name)) {
            throw new InvalidNameFormatForCreationException("Некорректный формат имени игрока");
        } else if (player1Name.equalsIgnoreCase(player2Name)) {
            throw new SamePlayersException("Необходимо указать разных игроков");
        }
        Optional<Player> player1Opt = playerDAO.getByName(player1Name.toUpperCase());
        Optional<Player> player2Opt = playerDAO.getByName(player2Name.toUpperCase());
        Player player1 = player1Opt.isEmpty() ? playerDAO.save(player1Name.toUpperCase()) : player1Opt.get();
        Player player2 = player2Opt.isEmpty() ? playerDAO.save(player2Name.toUpperCase()) : player2Opt.get();
        String currentMatchUUID = ongoingMatchesService.createOngoingMatch(player1, player2);
        response.sendRedirect(request.getContextPath() + "/match-score" + "?uuid=" + URLEncoder.encode(currentMatchUUID, StandardCharsets.UTF_8));
    }


}

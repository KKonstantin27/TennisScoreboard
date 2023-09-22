package controllers;

import models.Match;

import models.Page;
import models.Player;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


@WebServlet(name = "MatchListServlet", value = "/matches")
public class MatchListServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        configUTF(request, response);
        int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        String playerName = request.getParameter("filter_by_player_name");
        List<Match> matches;
        if (playerName == null) {
            matches = finishedMatchesService.readFinishedMatches();
        } else {
            Player player = playerDAO.getByName(playerName.toUpperCase());
            matches = finishedMatchesService.readFinishedMatch(player);
            request.setAttribute("filter_by_player_name", playerName);
        }
        Page page = new Page(matches, matches.size(), currentPage);
        List<Match> currentPageMatches = page.getCurrentPageMatches();
        setMatchListAttributes(request, response, currentPageMatches, page);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/matchList.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        configUTF(request, response);
        String currentPage = request.getParameter("page") == null ? "1" : request.getParameter("page");
        String playerName = request.getParameter("filter_by_player_name");
        String url;
        if (playerName == null) {
            url = "/matches" + "?page=" + URLEncoder.encode(currentPage, StandardCharsets.UTF_8);
        } else {
            url = "/matches" + "?page=" + URLEncoder.encode(currentPage, StandardCharsets.UTF_8)
                    + "&filter_by_player_name=" + URLEncoder.encode(playerName, StandardCharsets.UTF_8);
        }
        response.sendRedirect(url);
    }
}

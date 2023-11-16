package controllers;

import dto.MatchDTO;
import exceptions.InvalidNameFormatForSearchException;
import exceptions.PlayerDoesNotExistException;
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
import java.util.Optional;


@WebServlet(name = "MatchListServlet", value = "/matches")
public class MatchListServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        String playerName = request.getParameter("filter_by_player_name");
        List<Match> matches;
        if (playerName == null) {
            matches = finishedMatchesService.readFinishedMatches();
            prepareMatchListContent(request, response, matches, currentPage);
        } else if (validator.isValidUserInput(playerName)) {
            Optional<Player> playerOPT = playerDAO.getByName(playerName.toUpperCase());
            if (playerOPT.isEmpty()) {
                matches = finishedMatchesService.readFinishedMatches();
                prepareMatchListContent(request, response, matches, currentPage);
                throw new PlayerDoesNotExistException("Игрок отсутствует в БД");
            } else {
                request.setAttribute("filter_by_player_name", playerName);
                matches = finishedMatchesService.readFinishedMatch(playerOPT.get());
                prepareMatchListContent(request, response, matches, currentPage);
            }
        } else {
            matches = finishedMatchesService.readFinishedMatches();
            prepareMatchListContent(request, response, matches, currentPage);
            throw new InvalidNameFormatForSearchException("Некорректный формат имени игрока");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/matchList.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String currentPage = request.getParameter("page") == null ? "1" : request.getParameter("page");
        String playerName = request.getParameter("filter_by_player_name");
        String url;
        if (playerName == null) {
            url = request.getContextPath() + "/matches" + "?page=" + URLEncoder.encode(currentPage, StandardCharsets.UTF_8);
        } else {
            url = request.getContextPath() + "/matches" + "?page=" + URLEncoder.encode(currentPage, StandardCharsets.UTF_8)
                    + "&filter_by_player_name=" + URLEncoder.encode(playerName, StandardCharsets.UTF_8);
        }
        response.sendRedirect(url);
    }

    private void prepareMatchListContent(HttpServletRequest request, HttpServletResponse response,
                                           List<Match> matches, int currentPage) {
        List<MatchDTO> matchesDTO = mapper.convertToDTO(matches);
        Page page = new Page(matchesDTO, matches.size(), currentPage);
        List<MatchDTO> currentPageMatchesDTO = page.getCurrentPageMatches();
        setMatchListAttributes(request, response, currentPageMatchesDTO, page);
    }
}

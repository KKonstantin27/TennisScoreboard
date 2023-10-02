package controllers;

import DTO.MatchScoreDTO;
import models.MatchScore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@WebServlet(name = "MatchScoreServlet", value = "/match-score")
public class MatchScoreServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        configUTF(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/ongoingMatch.jsp");
        MatchScore matchScore = ongoingMatchesService.getOngoingMatch(UUID.fromString(request.getParameter("uuid")));
        setMatchAttributes(request, response, mapper.convertToDTO(matchScore));
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        configUTF(request, response);
        int scoringPlayerID = Integer.parseInt(request.getParameter("scoringPlayerID"));
        UUID uuid = UUID.fromString(request.getParameter("uuid"));
        MatchScore matchScore = ongoingMatchesService.getOngoingMatch(uuid);
        System.out.println(matchScore);
        if (matchScore == null) {
            response.sendRedirect(request.getContextPath() + "/main-page-redirect");
            return;
        }
        matchScore = calculationService.calculate(scoringPlayerID, matchScore);
        MatchScoreDTO matchScoreDTO = mapper.convertToDTO(matchScore);
        if (matchScore.isMatchEnded()) {
            ongoingMatchesService.removeOngoingMatch(uuid);
            int matchID = finishedMatchesService.saveFinishedMatch(matchScore.getMatch());
            setMatchAttributes(request, response, matchScoreDTO);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/finishedMatch.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/match-score" + "?uuid=" + URLEncoder.encode(uuid.toString(), StandardCharsets.UTF_8));
        }
    }
}

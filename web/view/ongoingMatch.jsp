<%@ page import="models.Player" %>
<%@ page import="java.lang.String" %>
<%@ page import="DTO.MatchScoreDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
  Created by IntelliJ IDEA.
  User: Konstantin
  Date: 07.09.2023
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Ongoing match</title>
    <link rel="stylesheet" href="view/styles.css" />
  </head>
  <body>
  <div class="body-up">
      <h1>Текущий матч</h1>
      <div class="ongoingMatch-scoreboard-base">
          <div class="ongoingMatch-scoreboard-border">
              <div class="ongoingMatch-scoreboard-innerPart">
                  <div class="ongoingMatch-match-id"><h2 class="ongoingMatch-match-id-text">UUID: <c:out value="${ongoingMatch.uuid}" /></h2></div>
                  <div class="ongoingMatch-first-line">
                      <span class="ongoingMatch-player"><c:out value="${ongoingMatch.match.player1.name}"/></span>
                      <form class="ongoingMatch-form" method="post" action="/match-score?uuid=<c:out value="${ongoingMatch.uuid}" />">
                          <button class="ongoingMatch-submit" type="submit" name="scoringPlayerID" value="<c:out value="${ongoingMatch.match.player1.id}"/>">+</button>
                      </form>
                      <span class="ongoingMatch-gameArea">
                          <c:out value="${ongoingMatch.p1CurrentScore}"/>
                      </span>
                      <c:forEach var="p1SetScore" items="${p1SetScores}">
                          <span class="ongoingMatch-setArea">
                              <c:out value="${p1SetScore.value}"/>
                          </span>
                      </c:forEach>
                  </div>
                  <div class="ongoingMatch-second-line">
                      <span class="ongoingMatch-score">SCORE</span>
                      <span class="ongoingMatch-game">GAME</span>
                      <span class="ongoingMatch-set">SET1</span>
                      <span class="ongoingMatch-set">SET2</span>
                      <span class="ongoingMatch-set3">SET3</span>
                  </div>
                  <div class="ongoingMatch-third-line">
                      <span class="ongoingMatch-player"><c:out value="${ongoingMatch.match.player2.name}"/></span>
                      <form class="ongoingMatch-form" method="post" action="/match-score?uuid=<c:out value="${ongoingMatch.uuid}" />">
                          <button class="ongoingMatch-submit" type="submit" name="scoringPlayerID" value="<c:out value="${ongoingMatch.match.player2.id}"/>">+</button>
                      </form>
                      <span class="ongoingMatch-gameArea">
                          <c:out value="${ongoingMatch.p2CurrentScore}"/>
                      </span>
                      <c:forEach var="p2SetScore" items="${p2SetScores}">
                          <span class="ongoingMatch-setArea">
                              <c:out value="${p2SetScore.value}"/>
                          </span>
                      </c:forEach>
                  </div>
              </div>
          </div>
      </div>
  </div>
  <div class="body-down">
      <div class="return-footer-div">
          <footer>icon by <a target="_blank" href="https://icons8.com"> Icons8</a></footer>
      </div>
  </div>
  </body>
</html>

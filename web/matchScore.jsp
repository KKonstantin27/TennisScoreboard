<%@ page import="models.MatchScore" %>
<%@ page import="models.Player" %>
<%@ page import="java.lang.String" %><%--
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
    <title>MatchScore</title>
    <link rel="stylesheet" href="styles.css" />
  </head>
  <body>
    <h1>Текущий матч</h1>
    <div class="score-board-border">
      <div class="border">
        <div class="inner-part">
          <div class="match-id"><h2 class="match-id-text">ID: <%= request.getParameter("uuid") %></h2></div>
          <div class="first-line">
            <span class="player1"><%= ((Player) request.getAttribute("Player1")).getName() %></span>
            <form class="score-up-form" method="post" action="/TennisScoreboard_war_exploded/match-score">
              <button class="score-up-submit" type="submit" name="ID" value="<%= ((Player) request.getAttribute("Player1")).getId() %>">+</button>
            </form>
            <span class="game-area">
              <% MatchScore matchScore = (MatchScore) request.getAttribute("MatchScore");%>
              <%=String.format("%02d", matchScore.getP1CurrentScore())%>
            </span>
            <span class="set-area">
              <%= matchScore.getP1SetScore().get(1)%>
            </span>
            <span class="set-area">
              <%= matchScore.getP1SetScore().get(2)%>
            </span>
            <span class="set-area">
              <%= matchScore.getP1SetScore().get(3)%>
            </span>
          </div>
          <div class="second-line">
            <span class="score">SCORE</span>
            <span class="game">GAME</span>
            <span class="set1">SET1</span>
            <span class="set2">SET2</span>
            <span class="set3">SET3</span>
          </div>
          <div class="third-line">
            <span class="player2"><%= ((Player) request.getAttribute("Player2")).getName() %></span>
            <form class="score-up-form" method="post" action="">
              <button class="score-up-submit" type="submit" name="ID" value="<%= ((Player) request.getAttribute("Player2")).getId() %>">+</button>
            </form>
            <span class="game-area">
              <%=String.format("%02d", matchScore.getP2CurrentScore())%>
            </span>
            <span class="set-area">
              <%= matchScore.getP2SetScore().get(1)%>
            </span>
            <span class="set-area">
              <%= matchScore.getP2SetScore().get(2)%>
            </span>
            <span class="set-area">
              <%= matchScore.getP2SetScore().get(3)%>
            </span>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>

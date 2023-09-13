<%@ page import="models.MatchScore" %><%--
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
            <span class="player1"><%= request.getAttribute("Player1") %></span>
            <form class="score-up-form" method="post" action="">
              <input class="score-up-submit" type="submit" value="+" />
            </form>
            <span class="game-area">
              <% MatchScore match = (MatchScore) request.getAttribute("MatchScore");%>
              <%=String.format("%02d", match.getP1Score())%>
            </span>
            <span class="set-area">
              <%= match.getP1Set1()%>
            </span>
            <span class="set-area">
              <%= match.getP1Set2()%>
            </span>
            <span class="set-area">
              <%= match.getP1Set1()%>
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
            <span class="player2"><%= request.getAttribute("Player2") %></span>
            <form class="score-up-form" method="post" action="">
              <input class="score-up-submit" type="submit" value="+" />
            </form>
            <span class="game-area">
              <%=String.format("%02d", match.getP2Score())%>
            </span>
            <span class="set-area">
              <%= match.getP2Set1()%>
            </span>
            <span class="set-area">
              <%= match.getP2Set2()%>
            </span>
            <span class="set-area">
              <%= match.getP2Set3()%>
            </span>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>

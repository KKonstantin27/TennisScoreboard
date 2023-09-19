<%@ page import="models.MatchScore" %>
<%@ page import="models.Player" %>
<%@ page import="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
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
    <title>Finished Match</title>
    <link rel="stylesheet" href="view/styles.css" />
  </head>
  <body>
    <h1>Завершённый матч</h1>
    <div class="score-board-border">
      <div class="border">
        <div class="inner-part">
          <div class="match-id"><h2 class="match-id-text">ID: <c:out value="${MatchID}" /></h2></div>
          <div class="first-line">
            <span class="player1"><c:out value="${P1Name}"/></span>
            <span class="winner-area">
              <span class="winner">

              </span>
            </span>
            <span class="game-area">
              <c:if test="${IsPlayer1Winner == true}">
                <img src="/img/cup_icon.png">
              </c:if>
            </span>
            <span class="set-area">
              <c:out value="${P1Set1Score}"/>
            </span>
            <span class="set-area">
              <c:out value="${P1Set2Score}"/>
            </span>
            <span class="set-area">
              <c:out value="${P1Set3Score}"/>
            </span>
          </div>
          <div class="second-line">
            <span class="score"></span>
            <span class="game">WINNER</span>
            <span class="set1">SET1</span>
            <span class="set2">SET2</span>
            <span class="set3">SET3</span>
          </div>
          <div class="third-line">
            <span class="player2"><c:out value="${P2Name}"/></span>
            <span class="winner-area">
              <span class="winner">



              </span>
            </span>
            <span class="game-area">
              <c:if test="${IsPlayer1Winner == false}">
                <img src="/img/cup_icon.png">
              </c:if>
            </span>
            <span class="set-area">
              <c:out value="${P2Set1Score}"/>
            </span>
            <span class="set-area">
              <c:out value="${P2Set2Score}"/>
            </span>
            <span class="set-area">
              <c:out value="${P2Set3Score}"/>
            </span>
          </div>
        </div>
      </div>
    </div>
  </body>

</html>

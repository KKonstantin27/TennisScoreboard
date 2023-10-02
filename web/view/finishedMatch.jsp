<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Finished match</title>
    <link rel="stylesheet" href="view/styles.css" />
  </head>
  <body>
  <div class="body-up">
      <h1>Завершённый матч</h1>
      <div class="ongoingMatch-scoreboard-base">
          <div class="ongoingMatch-scoreboard-border">
              <div class="ongoingMatch-scoreboard-innerPart">
                  <div class="ongoingMatch-match-id"><h2 class="ongoingMatch-match-id-text">ID: <c:out value="${ongoingMatch.match.id}" /></h2></div>
                  <div class="ongoingMatch-first-line">
                      <span class="ongoingMatch-player"><c:out value="${ongoingMatch.match.player1.name}"/></span>
                      <span class="finishedMatch-insteadForm">
                        <span class="finishedMatch-insteadButton"> </span>
                      </span>
                      <span class="ongoingMatch-gameArea">
                        <c:if test="${ongoingMatch.match.player1 == ongoingMatch.match.winner}">
                            <img src="${pageContext.request.contextPath}/img/cup_icon.png">
                        </c:if>
                      </span>
                      <c:forEach var="p1SetScore" items="${p1SetScores}">
                          <span class="ongoingMatch-setArea">
                              <c:out value="${p1SetScore.value}"/>
                          </span>
                      </c:forEach>
                  </div>
                  <div class="ongoingMatch-second-line">
                      <span class="ongoingMatch-score"></span>
                      <span class="ongoingMatch-game">WINNER</span>
                      <span class="ongoingMatch-set">SET1</span>
                      <span class="ongoingMatch-set">SET2</span>
                      <span class="ongoingMatch-set3">SET3</span>
                  </div>
                  <div class="ongoingMatch-third-line">
                      <span class="ongoingMatch-player"><c:out value="${ongoingMatch.match.player2.name}"/></span>
                      <span class="finishedMatch-insteadForm">
                        <span class="finishedMatch-insteadButton"> </span>
                      </span>
                      <span class="ongoingMatch-gameArea">
                        <c:if test="${ongoingMatch.match.player2 == ongoingMatch.match.winner}">
                            <img src="${pageContext.request.contextPath}/img/cup_icon.png">
                        </c:if>
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
          <div class="main-page-return"><a href="${pageContext.request.contextPath}/">Главная страница</a></div>
          <footer>icon by <a target="_blank" href="https://icons8.com"> Icons8</a></footer>
      </div>
  </div>
  </body>
</html>

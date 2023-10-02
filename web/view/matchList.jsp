<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Match list</title>
    <link rel="stylesheet" href="view/styles.css" />
  </head>
  <body>
  <div class="body-up">
    <div class="matchList-up">
      <h1>Список завершённых матчей</h1>
      <div class="matchList-search-area-base">
        <div class="matchList-filter"> </div>
        <div class="matchList-search-area-outerPart">
          <div class="matchList-search-area-innerPart"><h2>Поиск матчей игрока</h2></div>
          <div class="matchList-search-formArea">
            <form class="matchList-search-form" method="post" action="${pageContext.request.contextPath}/matches">
              <label for="playerName"><input class="matchList-search-input" id="playerName" name="filter_by_player_name" type="text" required placeholder="РОДЖЕР ФЕДЕРЕР" /></label>
              <input class="matchList-search-button" type="submit" value="Поиск матчей" />
            </form>
          </div>
        </div>
        <div class="matchList-filter">
          <c:if test = "${error != null}">
            <div class="matchList-error">
              <c:out value="${error}" />
            </div>
          </c:if>
          <c:if test = "${filter_by_player_name != null}">
            <c:out value="${filter_by_player_name}" />
            <form class="matchList-filter-form" method="get" action="${pageContext.request.contextPath}/matches">
              <input class="matchList-filter-button" type="submit" value="X" />
            </form>
          </c:if>
        </div>
      </div>
      <c:forEach var="match" items="${matches}">
        <div class="matchList-result-line">
          <c:if test = "${match != null}">
            <div class="matchList-result-line">
              <div class="matchList-leftSide">
                <div class="matchList-id">ID: <c:out value="${match.id}"/></div>
                <div class="matchList-text">
                  <img class="matchList-player-icon" src="${pageContext.request.contextPath}/img/p1_icon.png">
                  <c:out value="${match.player1.name}"/>
                </div>
              </div>
              <div class="matchList-center">
                <div class="matchList-cup-icon matchList-left-cup-icon">
                  <c:if test = "${match.winner == match.player1}"> <img src="${pageContext.request.contextPath}/img/cup_small_icon.png"> </c:if>
                </div>
                <div class="matchList-text matchList-center-text"> VS </div>
                <div class="matchList-cup-icon matchList-right-cup-icon">
                  <c:if test = "${match.winner == match.player2}"> <img src="${pageContext.request.contextPath}/img/cup_small_icon.png"> </c:if>
                </div>
              </div>
              <div class="matchList-rightSide">
                <div class="matchList-text">
                  <c:out value="${match.player2.name}"/>
                  <img class="matchList-player-icon" src="${pageContext.request.contextPath}/img/p2_icon.png">
                </div>
                <div class="matchList-id"></div>
              </div>
            </div>
          </c:if>
        </div>
      </c:forEach>
    </div>
    <div class="matchList-navigation">
      <div class="matchList-page-selector">
        <div class="matchList-page-button-area">
          <form method="post" action="${pageContext.request.contextPath}/matches?page=<c:out value="${page - 1}" /><c:if test = "${filter_by_player_name != null}">&filter_by_player_name=<c:out value="${filter_by_player_name}" /></c:if>" name="prev" >
            <input class="matchList-page-button" type="submit" value="<" <c:if test = "${page == 1}"> disabled </c:if> />
          </form>
        </div>
        <div class="matchList-page-number"><c:out value="${page}"/></div>
        <div class="matchList-page-button-area">
          <form method="post" action="${pageContext.request.contextPath}/matches?page=<c:out value="${page + 1}" /><c:if test = "${filter_by_player_name != null}">&filter_by_player_name=<c:out value="${filter_by_player_name}" /></c:if>" name="next" >
            <input class="matchList-page-button" type="submit" value=">" <c:if test = "${page == lastPage}"> disabled </c:if> />
          </form>
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

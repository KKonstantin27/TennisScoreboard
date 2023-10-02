<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>New match</title>
    <link rel="stylesheet" href="view/styles.css" />
  </head>
  <body>
    <div class="body-up">
      <h1>Новый матч</h1>
      <form class="newMatch-form" method="post" action="${pageContext.request.contextPath}/new-match">
        <div class="newMatch-div">
          <div class="newMatch-icon">
            <img src="${pageContext.request.contextPath}/img/p1_icon.png">
          </div>
          <div class="newMatch-inner-div">
            <label for="first-player-name">
              <h2 class="newMatch-text">Имя первого игрока</h2>
              <input class="newMatch-input" id="first-player-name" name="first-player-name" type="text" required placeholder="РОДЖЕР ФЕДЕРЕР"/>
            </label>
          </div>
        </div>

        <div class="newMatch-div">
          <div class="newMatch-icon">
            <img src="${pageContext.request.contextPath}/img/p2_icon.png">
          </div>
          <div class="newMatch-inner-div">
            <label for="second-player-name">
              <h2 class="newMatch-text">Имя второго игрока</h2>
              <input class="newMatch-input" id="second-player-name" name="second-player-name" type="text" required placeholder="РАФАЭЛЬ НАДАЛЬ"/>
            </label>
          </div>
        </div>
        <div class="newMatch-div"><input class="newMatch-button" type="submit" value="Создать матч" /></div>
      </form>
      <div class="newMatch-error">
        <c:if test = "${error != null}">
            <c:out value="${error}" />
        </c:if>
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

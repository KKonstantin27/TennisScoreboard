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
    <title>Tennis scoreboard</title>
    <link rel="stylesheet" href="view/styles.css" />
  </head>
  <body>
    <div class="body-up">
      <h1>Табло теннисного матча</h1>
      <div class="index-div">
        <img src="/img/new_match_icon.png">
        <h2 class="new-match"><a href="/new-match">Новый матч</a></h2>
      </div>
      <div class="index-div">
        <img src="/img/match_list_icon.png">
        <h2 class="index-list"><a href="/matches">Список завершённых матчей</a></h2>
      </div>
    </div>
    <div class="body-down">
      <div class="return-footer-div">
        <footer>icon by <a target="_blank" href="https://icons8.com"> Icons8</a></footer>
      </div>
    </div>
  </body>
</html>

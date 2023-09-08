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
    <title>NewMatch</title>
    <link rel="stylesheet" href="styles.css" />
  </head>
  <body>
    <h1>Новый Матч</h1>
    <form method="post" action="new-match">
      <div class="first-player-name"><label for="first-player-name"><h2>Имя первого игрока</h2> <input id="first-player-name" name="first-player-name" type="text" required placeholder="Роджер Федерер" /></label></div>
      <div class="second-player-name"><label for="second-player-name"><h2>Имя второго игрока</h2> <input id="second-player-name" name="second-player-name" type="text" required placeholder="Рафаэль Надаль"/></label></div>
      <div class="submit"><input class="button" type="submit" value="Создать матч" /></div>
    </form>
    <p class="main-page-return"><a href="index.jsp">Главная страница</a></p>

  </body>
</html>

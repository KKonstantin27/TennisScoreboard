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

      <div class="section first-player-name"><div class="div-icons"><img src="44.png"></div><div><label for="first-player-name"><h2>Имя первого игрока</h2> <input id="first-player-name" name="first-player-name" type="text" required placeholder="РОДЖЕР ФЕДЕРЕР" /></label></div></div>
      <div class="section second-player-name"><div class="div-icons"><img src="33.png"></div><div><label for="second-player-name"><h2>Имя второго игрока</h2> <input id="second-player-name" name="second-player-name" type="text" required placeholder="РАФАЭЛЬ НАДАЛЬ"/></label></div></div>
      <div class="section submit"><input class="button" type="submit" value="Создать матч" /></div>
    </form>
    <p class="main-page-return"><a href="index.jsp">Главная страница</a></p>

  </body>
</html>

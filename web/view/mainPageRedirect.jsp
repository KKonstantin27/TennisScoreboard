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
    <h1>Перенаправление на главную страницу</h1>
    <h2>Доступ к завершённыму матчу действителен только после окончания этого матча</h2>
    <h2><div class="main-page-redirect-return"><img src="/img/home_icon.png"><a href="${pageContext.request.contextPath}/">Главная страница</a></div></h2>
</div>
<div class="body-down">
    <div class="return-footer-div">
        <footer>icon by <a target="_blank" href="https://icons8.com"> Icons8</a></footer>
    </div>
</div>
</body>
</html>

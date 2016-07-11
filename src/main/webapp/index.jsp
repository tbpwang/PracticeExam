<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/6
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index Page</title>
</head>
<body>
<div>
    <h1>用户登录窗口</h1>
    <div>
        <form action="/user" method="post">
            <input type="hidden" name="action" value="login">
            用户名 <input type="text" id="username" name="username" placeholder="USERNAME"><br>
            密  码 <input type="password" id="password" name="password" placeholder="PASSWORD"><br>
            <br>
            <input type="submit" value="用户登录">
        </form>
    </div>
    <p>${requestScope.message}</p>
    <hr>
    <div>
        <a href="register.jsp">用户注册</a>
    </div>
    <div>
        <a href="/article?action=queryForUser"></a>
    </div>
</div>
</body>
</html>

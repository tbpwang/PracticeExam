<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/11
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Article Page</title>
    <style>

    </style>
    <script src="../js/jquery-3.0.0.min.js"></script>
    <script>

    </script>
</head>
<body>
<c:if test="${sessionScope.role ne 'admin'}">
    <c:redirect url="/index.jsp"/>
</c:if>
<h1>管理员修改文章</h1>
${sessionScope.username}
<hr>
<h3>发布新文章</h3>
<form action="/article" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${sessionScope.article.id}">
    文章标题： <input type="text" name="title" value="${sessionScope.article.title}"><br>
    文章摘要： <textarea name="abstract">${sessionScope.article.abstracts}</textarea><br>
    文章内容： <textarea name="content">${sessionScope.article.content}</textarea> <br>
    <input type="submit" value="保存">
</form>
</body>
</html>

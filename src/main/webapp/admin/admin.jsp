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
    <title>Admin Page</title>
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
<h1>管理员维护养生类知识文章</h1>
${sessionScope.username}
<hr>
<table>
    <tr style="background: #dddddd">
        <th width="50">序号</th>
        <th width="500">文章标题</th>
        <th width="200">发布时间</th>
        <th width="200" colspan="2">操作</th>
    </tr>
    <c:forEach var="article" items="${sessionScope.articles}" varStatus="vs">
        <tr>
            <td>${vs.count}</td>
            <td><a href="/article?action=queryForView&id=${article.id}">${article.title}</a></td>
            <td>${article.createDate}</td>
            <td><a href="/article?action=queryForEdit&id=${article.id}">修改</a></td>
            <td><a id="remove" href="/article?action=remove&id=${article.id}">删除</a></td>
        </tr>
    </c:forEach>
</table>
<hr>
<h3>发布新文章</h3>
<form action="/article" method="post">
    <input type="hidden" name="action" value="create">
    文章标题： <input type="text" name="title"><br>
    文章摘要： <textarea name="abstract"></textarea><br>
    文章内容： <textarea name="content"></textarea><br>
    <input type="submit" value="发布">
</form>
<br>
<p><a href="/user?action=logout">注销</a></p>
</body>
</html>

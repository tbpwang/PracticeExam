<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/11
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Page</title>
    <style>
        #welcome_info{
            text-align: right;
        }
    </style>

</head>
<body>
<h1>用户查询</h1>
<hr>
<div id="welcome_info">
    ${sessionScope.username}
</div>
<div>
    <form action="/article" method="post">
        <input type="hidden" name="action" value="queryByKey">
        标题关键字：<input type="text" name="title_keyword">
        内容关键字：<input type="text" name="content_keyword">
        <input type="submit" value="查询">
    </form>
</div>
<div>
    <table>
        <tr style="background: #dddddd">
            <th width="50">序号</th>
            <th width="500">文章标题</th>
            <th width="200">发布时间</th>
        </tr>
        <c:forEach var="article" items="${sessionScope.articles}" varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td><a href="/article?action=queryForView&id=${article.id}">${article.title}</a></td>
                <td>${article.createDate}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<p><a href="/user?action=logout">注销</a></p>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/11
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Article Page</title>
    <style>
        div{
            margin: 50px;;
        }
        #container{
            width: 1000px;
            margin: auto;
        }
        #title{
            text-align: center;
            font-weight: bold;
        }
        #date{
            text-align: right;
        }
        #abstract,
        #content{
            text-indent: 2em;
        }
    </style>
</head>
<body>
<c:if test="${sessionScope.role eq null}">
    <c:redirect url="/index.jsp"/>
</c:if>
<h1>文章详细</h1>
<div id="container">
    <div id="title">${sessionScope.article.title}</div>
    <div id="date">发布时间：${sessionScope.article.createDate}</div>
    <div id="abstract">摘要：${sessionScope.article.abstracts}</div>
    <div id="content">正文：${sessionScope.article.content}</div>
</div>
<br>
<p><a href="/user?action=logout">注销</a></p>
</body>
</html>

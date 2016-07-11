<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/9
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Register Page</title>
    <script src="js/jquery-3.0.0.min.js"></script>
    <script>
        $(function () {
            $('form').submit(function () {
                if ($('#username').val().trim() == '') {
                    alert("用户名不能为空，请重新输入");
                    $('#username').focus();
                    return false;
                }
                if ($('#password').val() == '') {
                    alert("密码不能为空，请重新输入");
                    $('#password').focus();
                    return false;
                }
                if ($('#re_password').val() == '') {
                    alert("重复密码不能为空，请重新输入");
                    $('#re_password').focus();
                    return false;
                }
                if ($('#re_password').val() != $('#password').val()) {
                    alert("两次输入密码不一致，请重新输入");
                    $('#re_password').focus();
                    return false;
                }
                if ($('#telephone').val().trim() == '') {
                    alert("联系电话不能为空，请重新输入");
                    $('#telephone').focus();
                    return false;
                }if ($('#address').val().trim() == '') {
                    alert("联系人地址不能为空，请重新输入");
                    $('#address').focus();
                    return false;
                }
                return true;
            });
        });
    </script>
</head>
<body>
<h1>普通用户注册</h1>
<div>
    <form action="/user" method="post">
        <input type="hidden" name="action" value="register">
        用户名   <input type="text" id="username" name="username" placeholder="USERNAME">*<br>
        密  码   <input type="password" id="password" name="password" placeholder="PASSWORD">*<br>
        确认密码 <input type="password" id="re_password" name="password" placeholder="PASSWORD">*<br>
        联系电话 <input type="text" id="telephone" name="telephone" placeholder="TELEPHONE">*<br>
        联系地址 <input type="text" id="address" name="address" placeholder="ADDRESS">*<br>
        <br>
        <input type="submit" value="用户注册">
    </form>
</div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: charwayh
  Date: 2019/3/3
  Time: 3:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<form action="LoginServlet" method="post ">
    用户名:<input type="text" name="uname"/><br/>
    密&nbsp;码：<input type="password" name="pwd"><br/>
    <input type="submit" value="登录">
    <input type="reset" value="重置">
</form>
</body>
</html>

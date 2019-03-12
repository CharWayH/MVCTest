<%--
  Created by IntelliJ IDEA.
  User: charwayh
  Date: 2019/3/3
  Time: 7:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
</head>
<body>
<form action="RegisterServlet" method="post">
    用户名:<input type="text" name="uname"/><br/>
    密&nbsp;码：<input type="password" name="pwd"><br/>
    <input type="submit" value="注册">
    <input type="reset" value="重置">
</form>
</body>
</html>

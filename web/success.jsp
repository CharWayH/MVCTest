<%--
  Created by IntelliJ IDEA.
  User: charwayh
  Date: 2019/3/3
  Time: 6:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册成功页</title>
</head>
<body>
<%
    String name = request.getParameter("uname");
    out.println(name + "注册成功");
%>
</body>
</html>

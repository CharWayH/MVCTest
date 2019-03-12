<%--
  Created by IntelliJ IDEA.
  User: charwayh
  Date: 2019/3/3
Time: 4:03 PM
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎！！！</title>
</head>
<body>
<%
    String uname = request.getParameter("uname");
    out.println(uname+"登录成功！！！");
%>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: charwayh
  Date: 2019/3/4
  Time: 12:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>create Excel</title>
</head>
<body>
<form method="post" action="ExcelServlet">
请输入年：<input type="text" name="year"><br/>
请输入月：<input type="text" name="month">
    <input type="submit" value="生成">
</form>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/5/29
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>首页</h1> <br/>
<!-- jsp中 / 和html中 /一样 都是ip:port
<%--    request.getContextPath()得到当前工程名  和${pageContext.request.contextPath} 一样--%>
    request.getServletPath()得到当前servlet请求的地址 如/index.jsp


-->
    <a href="<%=request.getContextPath()%>/toSuccess">success.jsp</a> <br/>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Hasan
  Date: 4.08.2021
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<%@include file="header.jsp"%>

<div>This is Main</div>


<jsp:include page="footer.jsp">
    <jsp:param name="parametername" value="parametervalue" />
</jsp:include>
</body>
</html>

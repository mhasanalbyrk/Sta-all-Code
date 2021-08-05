<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<a href="secondPage.jsp">Second Page</a>
<a href="ex4-servlet"> Ex 4 Servlet</a>
<a href="ex4.jsp"> Ex 4 JSP</a>
<a href="dispatcher-servlet?destination=dest1.jsp">Destination 1 ex-5</a>
<a href="login.jsp">Login</a>
<a href="login-servlet"> Admin login</a>
<a href="config">Config</a>
<div>
    <a href="atm-servlet?trans=withdraw&amount=100">draw 100</a>
    <a href="atm-servlet?trans=withdraw&amount=200">draw 200</a>
    <a href="atm-servlet?trans=withdraw&amount=500">draw 500</a>
    <a href="atm-servlet?trans=withdraw&amount=1000">draw 1000</a>
</div>

<div>
    <a href="atm-servlet?trans=deposit&amount=100">deposit 100</a>
    <a href="atm-servlet?trans=deposit&amount=200">deposit 200</a>
    <a href="atm-servlet?trans=deposit&amount=500">deposit 500</a>
    <a href="atm-servlet?trans=deposit&amount=1000">deposit 1000</a>
</div>
<a href="origin-servlet">Origin</a>
<a href="main.jsp">Include Main</a>
<a href="exception-servlet?error=5xx">Exception-5xx</a>
<a href="exception-servlet?error=404">404 not found</a>
<a href="create-session">Create Session</a>
<a href="private/secured.jsp">Secured console</a>


</body>
</html>
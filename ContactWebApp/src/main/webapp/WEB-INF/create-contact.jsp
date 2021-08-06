<%--
  Created by IntelliJ IDEA.
  User: Hasan
  Date: 4.08.2021
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Contact</title>
</head>
<body>
<form action="create-contact-servlet" method="POST" >
    <label for="name"> Name</label>
    <input type="text" id="name" name="name">
    <label for="number"> Tel Number</label>
    <input type="text" id="number" name="number" maxlength="10" minlength="10">

    <input type="submit" id="submit">
</form>
</body>
</html>

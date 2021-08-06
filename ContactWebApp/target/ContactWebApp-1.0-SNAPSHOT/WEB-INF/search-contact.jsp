<%--
  Created by IntelliJ IDEA.
  User: Hasan
  Date: 4.08.2021
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Search Contact</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>
<link rel="stylesheet" href="../style.css">
<body>
<form action="search-contact-servlet" method="POST">
    <label for="name">Name</label>
    <input type="text" id="name" name="name">
    <input type="submit">
</form>
<div>
    <c:if test="${Contacts != null}">

        <p><c:out value="Contacts "/></p>
        <c:forEach items="${Contacts}" var="contact">
            <ul>
            <c:out value=" Name:${contact.getName()}"/>
            <c:out value="| Number:${contact.getNumber()} ->"/>

            <form action="edit-contact-servlet" method="GET" >

                <input type="hidden" value="${contact.getId()}" name="id"  >

                <input type="submit" value="Edit" >
            </form>
            <a href="edit-contact-servlet?id=${contact.getId()}"> Edit </a>
                <p>
                    <c:out value="-----------------------------------------------------------"/>
                </p>
            </ul>

        </c:forEach>
    </c:if>
</div>
</body>
</html>

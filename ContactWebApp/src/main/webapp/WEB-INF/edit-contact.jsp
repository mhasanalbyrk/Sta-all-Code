<%--
  Created by IntelliJ IDEA.
  User: Hasan
  Date: 4.08.2021
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Contact</title>
</head>
<body>
<p>Contacts</p>
<div>
    <%--    <c:forEach items="${contacts}" var="contact">--%>
    <%--        <c:out value="${contact.getName()}"/>--%>
    <%--        <c:out value="${contact.getNumber()}"/>--%>

        <c:out value="${Contact.getName()} ---- ${Contact.getNumber()}"/>
    <form action="edit-contact-servlet" method="POST">
        <div><label for="name">New Name</label>
            <input type="text" id="name" name="name" value="${Contact.getName()}"></div>
        <div><label for="number"> <br>New Number</label>
            <input type="text" id="number" name="number" maxlength="10" minlength="10" value="${Contact.getNumber()}">
        </div>
        <input type="text" value="${id}" name="id" hidden>
        <input type="submit" value="Edit">
    </form>
    <%--        <a href="edit-contact?id=${contact.getId()}"> Edit</a>--%>
    <%--    </c:forEach>--%>
</div>
</body>
</html>

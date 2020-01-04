<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>profile</title>
</head>
<body>
    <h1>Your Profile</h1>
    <c:out value="${spitter.id}"/><br/>
    <c:out value="${spitter.username}"/><br/>
    <c:out value="${spitter.firstName}"/><br/>
    <c:out value="${spitter.lastName}"/>
</body>
</html>

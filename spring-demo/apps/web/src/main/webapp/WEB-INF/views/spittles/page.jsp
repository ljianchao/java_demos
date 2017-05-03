<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Spittr List</title>
</head>
<body>
<h1>Recent Spittles</h1>
<p>
<ul>
    <c:forEach items="${spittleList}" var="spittle">
        <li id="spittle_<c:out value="spittle.id"/> ">
            <div>
                <c:out value="${spittle.message}"/>
            </div>
            <div>
                <span><c:out value="${spittle.time}"/></span>
                <span>
                            (<c:out value="${spittle.latitude}"/>,
                            <c:out value="${spittle.longitude}"/>)
                        </span>
            </div>
        </li>
    </c:forEach>
</ul>
</p>
</body>
</html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>spittle</title>
</head>
<body>
    <div>
        <div> <c:out value="${spittle.message}"/> </div>
        <div>
            <span><c:out value="${spittle.time}"/> </span>
        </div>
    </div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Succès</title>
</head>
<body>
      <c:if test = "${not empty validation}">
          <p><c:out value="${validation}" escapeXml="false"/></div>
      </c:if>
</body>
</html>
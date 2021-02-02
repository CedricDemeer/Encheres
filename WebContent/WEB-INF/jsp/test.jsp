<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>   Affichage </h1>

	
	<c:forEach var="v" items="${liste}">
						<li>${v.getNom()}  ${v.getPrenom()}
						</li>
					</c:forEach>
					
					
					<form action="<%=request.getContextPath()%>/Test" method="post">
		 <br /> <br />
		<input type="submit" value="Valider" />
	</form>
					


</body>
</html>
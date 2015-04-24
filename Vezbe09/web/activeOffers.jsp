<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!-- TODO: change activeCategory to whatever the name should be -->
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>
	<c:out value="${activeCategory.name}"/>
</title>
</head>
<body>
	<h1>
		<c:out value="${activeCategory.name}"/>
		ponude:
	</h1>
	
	<table>
		<caption><b>Ponude u kategoriji:</b></caption>
		<tr>
			<th>Sifra</th>
			<th>Ime ponude</th>
			<th>Datum kreiranja</th>
			<th>Datum isticanja</th>
			<th>Cena</th>
			<th>Akcija</th>
			<th>Aktivnost</th>
		</tr>
		<c:forEach value="offer" items="${activeCategory.offers }">
			<tr>
				<td> <c:out value="${offer.id}"/> </td>
				<td> <c:out value="${offer.name}"/> </td>
				<td> <c:out value="${offer.dateCreated}"/> </td>
				<td> <c:out value="${offer.expirationDate}"/> </td>
				<td> <c:out value="${offer.regPrice}"/> </td>
				<td> <c:out value="${offer.salePrice}"/> </td>
				<td> <c:out value="${offer.active}"/> </td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
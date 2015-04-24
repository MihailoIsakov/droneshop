<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:setBundle basename="messages.messages"/>

<html>
	<head>
		<title><fmt:message key="dodavanjeVozila"/></title>
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
		<link href="./theme.css" rel="stylesheet" type="text/css" />
	</head>
	<c:if test="${sessionScope.admin==null}">
		<c:redirect url="./login.jsp" />
	</c:if>
	<body>
		<form action="./AddSeller?operation=ADD" method="post"class="dodavanjeVozilaForma" accept-charset="ISO-8859-1">
			<table class="dodavanjeVozilaTabela">
				<tr>
					<td><fmt:message key="firstName"/>:</td>
					<td><input type="text" name="firstName" ></td>			
				</tr>
				<tr>
					<td><fmt:message key="lastName"/>:</td>
					<td><input type="text" name="lastName" ></td>				
				</tr>
				<tr>
					<td><fmt:message key="username"/>:</td>
					<td><input type="text" name="username" ></td>				
				</tr>
				<tr>
					<td><fmt:message key="password"/>:</td>
					<td><input type="text" name="password" ></td>				
				</tr>
				<tr>
					<td><fmt:message key="phoneNum"/>:</td>
					<td><input type="text" name="phoneNum" ></td>				
				</tr>
				<tr>
					<td><fmt:message key="email"/>:</td>
					<td><input type="text" name="email" ></td>				
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" name="submit" value="<fmt:message key="dodaj"/>"></td>				
				</tr>
			</table>							
		</form>
		
		
		<form action="./RegisterAsBuyer" method="post" class="prijavaForma" accept-charset="ISO-8859-1">
			<table class="prijavaTabela">
				<tr>
					<td><fmt:message key="username"/>:</td>
					<td><input type="text" name="username">				
				</tr>
				<tr>
					<td><fmt:message key="password"/>:</td>
					<td><input type="text" name="password"></td>				
				</tr>
								<tr>
					<td><fmt:message key="firstName"/>:</td>
					<td><input type="text" name="firstName">				
				</tr>
				<tr>
					<td><fmt:message key="lastName"/>:</td>
					<td><input type="text" name="lastName"></td>				
				</tr>
					<tr>
					<td><fmt:message key="address"/>:</td>
					<td><input type="text" name="address"></td>				
				</tr>
								<tr>
					<td><fmt:message key="email"/>:</td>
					<td><input type="text" name="email">				
				</tr>
				<tr>
					<td><fmt:message key="phoneNum"/>:</td>
					<td><input type="text" name="phoneNum"></td>				
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" name="submit" value="<fmt:message key="prijaviSe"/>"></td>				
				</tr>	</table>						
		</form>
		
		[<a href="./ReadController"><fmt:message key="listaVozila"/></a>]<br/>
		[<a href="./LogoutController"><fmt:message key="odjava"/></a>]<br/>
	<body>
</html>
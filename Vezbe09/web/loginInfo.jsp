<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

 <!--  DEO KOJI GOVORI KORISNIKU DA LI JE ULOGOVAN -->
 
 
<c:choose>
    <c:when test="${user.ulogovan}">
        <p id="loginInfo" class="text-right" style="font-size:12px">Trenutno ste ulogovani kao 
        <c:if test="${user.admin }">
        administrator :
        </c:if>
        <c:if test="${!user.admin }">
        korisnik :
        </c:if>
        ${user.korisnickoIme}
       <Button onclick="odjaviMe()">Odjavi me<span class="glyphicon glyphicon-remove"></span></Button> </p>
    </c:when>
    <c:otherwise>
        <p id="loginInfo" class="text-right">Trenutno niste ulogovani.</p>
    </c:otherwise>
</c:choose>
  
</body>
</html>
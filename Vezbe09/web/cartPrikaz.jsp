<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Vaša kolica</title>

    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    
</head>
<body>
<jsp:include page="navigacija.jsp" />
<jsp:include page="loginInfo.jsp" />

<div class="container">

<table class="table">
	<caption><b>Ovo je sadržaj vaše korpe</b></caption>
	<tr>
	<th> Naziv </th>
	<th> Količina na računu </th>
	<th> Cena </th>

	<th> </th>
	</tr>
	<c:forEach var="ajtem" items="${user.korpa.mapaPredmeta}">
	<tr>
	<td>
    <c:out value="${ajtem.value.naziv}"/>
    </td>
    <td>
    <c:out value="${ajtem.value.kolicinaNaRacunu }"/>
    </td>
    <td>
    <c:out value="${ajtem.value.cena * ajtem.value.kolicinaNaRacunu }"/>
    </td>
    <td>
    <button class="glyphicon glyphicon-remove-circle" onclick="ukloniIzKorpe('<c:out value="${ajtem.value.naziv}"/>' )">Ukloni jedan</button>
    </td>
    </tr> 
</c:forEach>
</table>

	<button class="btn btn-primary" onclick="kreirajRacun()"> Naruči! </button>	



</div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
          <!-- Moja skripta -->
    <script src="webProjekatSkripta.js"></script>


</body>
</html>
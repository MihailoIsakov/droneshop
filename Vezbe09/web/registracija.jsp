
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrovanje novih korisnika</title>
    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Naknadni css -->
    <link rel="stylesheet" type="text/css" href="style.css">
    
    <link rel="stylesheet" href="http://jqueryvalidation.org/files/demo/site-demos.css">
    


</head>
<body>

<jsp:include page="navigacija.jsp" />



	<div class="col-md-2 col-md-offset-5 panel panel-default" align="left">
        <form id="regForm">
            <div class="form-group">
                <label for="username">Korisničko ime :</label>
                <input name="username" type="text" class="form-control">
            </div>    
            <div class="form-group">
                <label for="password">Lozinka :</label>
                <input name="password" type="password" class="form-control">
            </div>
            <div class="form-group">
                <label for="name">Ime :</label>
                <input name="name" type="text" class="form-control">
            </div>
            <div class="form-group">
                <label for="lastname">Prezime :</label>
                <input name="lastname" type="text" class="form-control">
            </div>
		  <div class="checkbox">
		    <label>
		      <input type="checkbox" name="admin"> Adminska prava
		    </label>
		  </div>
            <div class="form-group">
                <label for="contact">Kontakt telefon :</label>
                <input name="contact" type="text" class="form-control">
            </div>
            <div class="form-group">
                <label for="mail">E-mail :</label>
                <input name="mail" id="mail" type="text" class="form-control">
            </div>
            <button id="regButton" type="button" class="btn btn-primary" onclick="sendRegistrationPars()">Posalji</button>
        </form>
    </div>
	
	
	

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
          <!-- Moja skripta -->
    <script src="webProjekatSkripta.js"></script>

</body>
</html>
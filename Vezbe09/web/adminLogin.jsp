<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin log page</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
  

</head>
<body>

    <div class="col-md-4 col-md-offset-4 panel panel-default">
        <form id="adminForm">
            <div class="form-group">
                <label for="adminUsername">Admin name :</label>
                <input type="text" id="adminUsername" name="username" class="form-control">
            </div>    
            <div class="form-group">
                <label for="adminPassword">Password :</label>
                <input id="adminPassword" type="password" name="password" class="form-control">
            </div>
            <button id="adminLoginBtn" type="button" class="btn btn-primary" onclick="sendAdminLogPars()">Submit</button>
        </form>
    </div>
	
	<!-- JS SKRIPTE -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

          <!-- Moja skripta -->
    <script src="webProjekatSkripta.js"></script>

    
</body>
</html>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
  <head >
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sastavljanje uređaja</title>

    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Naknadni css -->
    <link rel="stylesheet" type="text/css" href="style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script>
    $(document).ready(function(){
        $("#pogledajUredjaj").click(function(){
        	var i=1;
        	$('#komponente :selected').each(function(i, selected){ 
        		  $("#ubaceneKomponente").append("<input type=\"text\" name=\"komponenta" + i + "\" value=\""+ $(selected).text() + "\">" + "<br>"); 
        		});
        }); 
        
        $("#kreirajUredjaj").click(function(){

        	$.ajax({
                type: 'post',
                url: 'KreirajUredjajServlet',
                dataType: 'text',
                data: $("#listaKom").serialize(),
                success: function (data) {
                    alert("Uspesno ste kreirali uređaj.");
                    window.location = "index.jsp";
                                },
                error: function(data,textStatus,errorThrown) {
                	alert("Greska sa kreiranjem uređaja");
                				},
            });
        }); 

});   
    </script>  
</head>
<body>

<jsp:include page="navigacija.jsp" />
<jsp:include page="loginInfo.jsp" />

<div class="container">
	<div class="col-md-12">
	<form id="noviUredjajForm">
		<fieldset>
			<legend> Sastavite svoj uređaj</legend>
		
		
					<!-- Select Multiple -->
			<div class="control-group uredjaji">
			  <label class="control-label" for="komponente">Odaberite komponente koje želite u uređaju :</label>
			  <div class="controls">
			    <select id="komponente" name="komponente" class="input-xlarge" multiple="multiple">
			    	<c:forEach var="ajtem" items="${komponente}">
						<option><c:out value="${ajtem}"/></option>
	    			</c:forEach>
	
			    </select>
			  </div>
			</div>
			<hr>
		</fieldset>
	</form>
		<button class="btn btn-primary" id="pogledajUredjaj"> Dodaj u uređaj </button>
			<hr>
				<p> <b> Uređaj se trenutno sastoji od : </b> </p>

			<form id="listaKom">
			
			<div class="control-group">
			  <label class="control-label" for="naziv">Naziv :</label>
			  <div class="controls">
			    <input id="naziv" name="naziv" type="text">
			  </div>
			</div>
			
						<!-- Textarea -->
			<div class="control-group">
			  <label class="control-label" for="opis">Opis :</label>
			  <div class="controls">                     
			    <textarea name="opis" id="opis"></textarea>
			  </div>
			</div>
			
			<div class="control-group">
			  <label class="control-label" for="opisInput">Komponente trenutno ubačene :</label>
			  <div class="controls" id="ubaceneKomponente">                     
			  </div>
			</div>
			
			</form>
			<hr>
			
			<button class="btn btn-danger" id="kreirajUredjaj"> Kreiraj Uređaj </button>

	</div>

</div>

 

    <script src="bootstrap/js/bootstrap.min.js"></script>
          <!-- Moja skripta -->
    <script src="webProjekatSkripta.js"></script>
  
</body>
</html>
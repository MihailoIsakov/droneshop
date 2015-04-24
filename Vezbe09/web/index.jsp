<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
  <head >
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>WebProjekat</title>

    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Naknadni css -->
    <link rel="stylesheet" type="text/css" href="style.css">
    
  
  </head>
  <body onload="pokaziKomponente()">
      
<jsp:include page="navigacija.jsp" />
<jsp:include page="loginInfo.jsp" />

 <!-- MIDDLE DEO ZA CLANKE -->
 

<div class="container">
    <div class="row">
      <div class="col-md-3 panel panel-default" >  
   		 <button id="pretraga" class="btn-block btn-secondary" >Pretraga</button>
      <div>
      
      <!-- Multiple Radios -->
<div id="kriterijumi">

<form id="queryPars" >
	<fieldset >
		
		<!-- Form Name -->
		<legend>Kriterijumi:</legend>
		
		<div class="control-group">
		  <div class="controls">
		    <label class="radio" for="radios-0" >
		      <input type="radio" name="radios" id="radios-0" value="Komponente" checked="checked" onclick="pokaziKomponente()">
		      Komponente
		    </label>
		    <label class="radio" for="radios-1" >
		      <input type="radio" name="radios" id="radios-1" value="Uredjaji" onclick="pokaziUredjaje()">
		      Uređaji
		    </label>
		  </div>
		</div>
		
		<!-- Text input-->
		<div class="control-group">
		  <label class="control-label" for="naziv">Naziv :</label>
		  <div class="controls">
		    <input id="naziv" name="naziv" type="text" >
		  </div>
		</div>
		
		<!-- Text input-->
		<div class="control-group komponente">
		  <label class="control-label" for="textinput">Cenovni raspon :</label>
		  <div>
		    <input name="minCena" type="text" placeholder="Donja granica">
		    <input name="maxCena" type="text" placeholder="Gornja granica">
		  </div>
		</div>
		
		<!-- Text input-->
		<div class="control-group komponente">
		  <label class="control-label" for="textinput">Raspoloživa količina :</label>
		  <div class="controls">
		    <input name="kolicina" type="text">
		  </div>
		</div>
		
		<!-- Textarea -->
		<div class="control-group">
		  <label class="control-label" for="opisInput">Unesite deo opisa :</label>
		  <div class="controls">                     
		    <textarea name="opis" id="opis"></textarea>
		  </div>
		</div>
		
		<!-- Select Basic -->
		<div class="control-group komponente">
		  <label class="control-label" for="selectKategorija">Kategorija :</label>
		  <div class="controls">
		    <select name="selectKategorija">
		      <option>Procesori</option>
		      <option>Memorija</option>
		      <option>Oprema za servere</option>
		      <option>Optički uređaji</option>
		      <option>Zvučne kartice</option>
		      <option>TV kartice</option>
		      <option>Matične ploče</option>
		      <option>Grafičke karte</option>
		      <option>Monitori</option>
		      <option>Hladnjaci i oprema</option>
		      <option>Napajanja</option>
		      <option>Kućišta</option>
		    </select>
		  </div>
		</div>
		
		<!-- Select Multiple -->
		<div class="control-group uredjaji">
		  <label class="control-label" for="komponente">Komponente :</label>
		  <div class="controls">
		    <select id="komponente" name="komponente" class="input-xlarge" style="width:180px" >
		    	<c:forEach var="ajtem" items="${komponente}">
					<option><c:out value="${ajtem}"/></option>
    			</c:forEach>

		    </select>
		  </div>
		</div>
		<hr>
		<div>
		<button class="btn btn-primary" onclick="sendQueryPars()" type="button">Pošalji</button>
		</div>
	</fieldset>
</form>
</div>
</div>	
		
      </div>
      <div class="col-md-7 ">
        <div class="list-group" id="content">
		</div>  
	  </div>   

      
  <!--  LOG IN DEO  -->
  <c:if test="${ !user.ulogovan }">
      <div class="col-md-2 panel panel-default " > 
          <form id="login" method="post">
          <p style="margin-top : 5px">Ulogujte se kao korisnik :</p>
          <hr>
            <div class="form-group">
                <label for="username"> Username : </label>
                <input id="username" name="username" type="text" class="form-control" placeholder="Pera Peric">
            </div>
            <div class="form-group">
                <label for="password"> Password : </label>
                <input id="password" type="password" name="password" class="form-control"  placeholder="*******">
            </div>
            <button id="loginBtn" onclick="sendLogPars()" value="Submit" class="btn btn-primary" type="button"> Uloguj se </button>
          </form>
          <br>
          <a href="registracija.jsp" style="cursor:pointer"> Registracija </a><br>
          <a href="adminLogin.jsp" style=" cursor:pointer  "> Admin login </a>
      </div>
  </c:if>
    </div>
</div>


<!-- DEO KOJI SLUZI PREBACIVANJU STRANA 
  <div align="center">
	     <ul class="pagination" >
	 		  <li><a href="#">&laquo;</a></li>
	  	      <li><a href="#">1</a></li>
			  <li><a href="#">2</a></li>
			  <li><a href="#">3</a></li>
			  <li><a href="#">4</a></li>
			  <li><a href="#">5</a></li>
			  <li><a href="#">&raquo;</a></li>
		</ul>
	</div>
	--> 

<!-- DIJALOG ZA SLANJE PORUKE TEHNICKOJ PODRSCI -->
  <div class="modal fade" id="contact" >
    <div class="modal-dialog">
      <div class="modal-content">
          <form class="form-horizontal" id="poruka-form">
                <div class="modal-header">
                  <h4> Kontaktirajte naše operatere </h4>
                </div>
                <div class="modal-body">
                
                    <p><strong>Vaš kontakt :</strong></p>
                    <input type="text" class="form-control" name="kontakt" > 
                    <p><strong> Tekst vaše poruke : </strong></p>
                    <textarea class="form-control" rows="5" name="poruka"></textarea>
                   
                    <div class="modal-footer">
                      <button class="btn btn-submit" type="button" onclick="posaljiPoruku()"> Dugme za slanje </button>
                      <a href="#" class="btn btn-info" data-dismiss="modal"> Zatvori </a>
                    </div>
                </div>
            </form>
          </div>
    </div>
  </div>
   
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
          <!-- Moja skripta -->
    <script src="webProjekatSkripta.js"></script>
  </body>
    
</html>
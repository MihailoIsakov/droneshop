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

<div class="container">
<div class="col-md-6">
      <!-- Multiple Radios -->



<form id="newEntityForm">
<fieldset >


<!-- Form Name -->
<legend>Unos novih entiteta u bazu:</legend>

<div class="control-group">
  <div class="controls">
    <label class="radio" for="radios-0" >
      <input type="radio" name="radios" id="radios-0" value="Komponente" checked="checked" onclick="pokaziKomponente()">
      Komponente
    </label>
    <label class="radio" for="radios-1" >
      <input type="radio" name="radios" id="radios-1" value="Uredjaji" onclick="pokaziUredjaje()">
      Uredjaji
    </label>
        <label class="radio" for="radios-2" >
      <input type="radio" name="radios" id="radios-2" value="Kategorije komponenti]" onclick="pokaziKategorije()">
      Kategorije komponenti
    </label>
  </div>
</div>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="nazivInput">Naziv :</label>
  <div class="controls">
    <input name="nazivInput" type="text" >
  </div>
</div>

<!-- Text input-->
<div class="control-group komponente">
  <label class="control-label" for="textinput">Cena</label>
  <div>
    <input name="cenaInput" type="text" >
  </div>
</div>

<!-- Text input-->
<div class="control-group komponente">
  <label class="control-label" for="textinput">Raspoloziva kolicina :</label>
  <div class="controls">
    <input name="kolicinaInput" type="text">
  </div>
</div>

<!-- Textarea -->
<div class="control-group">
  <label class="control-label" for="opisInput">Unesite opis :</label>
  <div class="controls">                     
    <textarea name="opisInput"></textarea>
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

<!-- Select Basic -->
<div class="control-group kategorije">
  <label class="control-label" for="selectPodkategorija">Podkategorija :</label>
  <div class="controls">
    <select name="selectPodkategorija">
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
		 <c:forEach var="komponenta" items="${ kategorije }">
			<option><c:out value="${komponenta}"/></option>
			<br>
		</c:forEach>
    </select>
  </div>
</div>

<!-- Select Basic -->
<div class="control-group komponente">
  <label class="control-label" for="selectbasic">Link ka oficijelnom sajtu :</label>
  <div class="controls">                     
    <textarea name="linkInput"></textarea>
  </div>
</div>

<!-- File Button --> 
<div class="control-group komponente">
  <label class="control-label" for="slikaInput">URL do slike :</label>
  <div class="controls">
    <input name="slikaInput" type="text">
  </div>
</div>

<!-- Select Multiple -->
<div class="control-group uredjaji">
  <label class="control-label" for="skomponenteInput">Komponente :</label>
  <div class="controls">
    <select name="komponenteInput" name="selectmultiple" class="input-xlarge" multiple="multiple">
		<c:forEach var="komponenta" items="${ komponente }">
			<option><c:out value="${komponenta}"/></option>
			<br>
		</c:forEach>
    </select>
  </div>
</div>

<hr>
<div>
<button class="btn btn-primary" onclick="sendNewEntityPars()">Kreiraj</button>
</div>
</fieldset>
</form>

</div>	
</div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
          <!-- Moja skripta -->
    <script src="webProjekatSkripta.js"></script>


</body>
</html>
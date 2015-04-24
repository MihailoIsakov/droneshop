<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
  <head >
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Naknadni css -->
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Izmena entiteta</title>
    
    <script>
    
    function dodavanje()
    {

    	$('#komponenteSve :selected').each(function(i, selected){ 
    		  $("#komponenteUredjaja").append("<option>" + $(selected).text() + "</option>"); 
    		});
    }

    function brisanje()
    {
    	$('#komponenteUredjaja :selected').remove(); 
    }
        
    </script>  
    
  </head>
<body>

<jsp:include page="navigacija.jsp" />
<jsp:include page="loginInfo.jsp" />


<div class="container">
<form id="changedEntityForm">
<fieldset >

<c:if test='${not empty komponenta}'>

<!-- Form Name -->
<legend>Menjate komponentu : <c:out value="${komponenta.naziv}" /></legend>

<!-- Text input-->
<div class="control-group komponente">
  <label class="control-label" for="nazivInput">Naziv</label>
  <div>
    <input name="nazivInput" type="text" value="${komponenta.naziv}" readonly>
  </div>
</div>

<!-- Text input-->
<div class="control-group komponente">
  <label class="control-label" for="textinput">Cena</label>
  <div>
    <input name="cenaInput" type="text" placeholder="${komponenta.cena}" >
  </div>
</div>

<!-- Text input-->
<div class="control-group komponente">
  <label class="control-label" for="textinput">Raspoloziva kolicina :</label>
  <div class="controls">
    <input name="kolicinaInput" type="text" placeholder="${komponenta.raspolozivaKolicina}">
  </div>
</div>

<!-- Textarea -->
<div class="control-group">
  <label class="control-label" for="opisInput">Unesite opis :</label>
  <div class="controls">                     
    <textarea name="opisInput" placeholder="${komponenta.opis}"></textarea>
  </div>
</div>

<!-- Select Basic -->
		<div class="control-group komponente">
		  <label class="control-label" for="selectKategorija">Kategorija :</label>
		  <div class="controls">
		    <select name="selectKategorija" >
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
<div class="control-group komponente">
  <label class="control-label" for="selectbasic">Link ka oficijelnom sajtu :</label>
  <div class="controls">                     
    <textarea name="linkInput" placeholder="${komponenta.link}"></textarea>
  </div>
</div>

<!-- File Button --> 
<div class="control-group komponente">
  <label class="control-label" for="slikaInput">URL do slike :</label>
  <div class="controls">
    <input name="slikaInput" type="text" placeholder="${komponenta.slika}">
  </div>
</div>

<hr>
<div>
<button class="btn btn-primary" onclick="sendEditedComponent()">Pošalji</button>
</div>

   
 </c:if>
 
<c:if test='${not empty uredjaj}'>
 
 <!-- Form Name -->
<legend>Menjate uređaj : <c:out value="${uredjaj.naziv}" /></legend>

<!-- Text input-->
<div class="control-group komponente">
  <label class="control-label" for="nazivInput">Naziv</label>
  <div>
    <input name="nazivInput" type="text" value="${uredjaj.naziv}" readonly>
  </div>
</div>
 
 <!-- Text input-->
<div class="control-group komponente">
  <label class="control-label" for="cenaInput">Cena</label>
  <div>
    <input name="cenaInput" type="text" placeholder="${uredjaj.cena}" >
  </div>
</div>

<!-- Textarea -->
<div class="control-group">
  <label class="control-label" for="opisInput">Unesite opis :</label>
  <div class="controls">                     
    <textarea name="opisInput" placeholder="${uredjaj.opis}"></textarea>
  </div>
</div>

<div class="row">
<div class="col-md-6" >
<b> Komponente u uređaju: </b> <br>
    <select id="komponenteUredjaja" name="komponenteUredjaja" class="input-xlarge" multiple="multiple">
		<c:forEach var="komponenta" items="${ uredjaj.spisakKomponenti }">
			<option><c:out value="${komponenta}"/></option>
			<br>
		</c:forEach>
	</select>


</div>
<div class="col-md-6" >
	<b> Sve dostupne komponente: </b> <br>
    <select id="komponenteSve" name="komponenteSve" class="input-xlarge" multiple="multiple">
		<c:forEach var="komponenta" items="${ komponente }">
			<option><c:out value="${komponenta}"/></option>
			<br>
		</c:forEach>
	</select>

</div>
</div>

  </c:if>

</fieldset>
</form>
 <c:if test='${not empty uredjaj}'>
<div class="row">
<div class="col-md-6" >
	<button class="btn btn-default" onclick="brisanje()">Ukloni iz uređaja</button>
</div>
<div class="col-md-6" >
	<button class="btn btn-default" onclick="dodavanje()">Dodaj u uređaj</button>
</div>

</div>

<hr>
<button class="btn btn-primary" onclick="sendEditedComponent()">Pošalji</button>
  </c:if>
</div>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
          <!-- Moja skripta -->
    <script src="webProjekatSkripta.js"></script>
</body>
</html>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
  <head >
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Izveštaj o poslovanju</title>

	    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

    <!-- Naknadni css -->
    <link rel="stylesheet" type="text/css" href="style.css">
    
    <script>
    
    function sakrijKategoriju()
    {
        $('.komponente').fadeToggle();
    }
    
    function saljiParametreRacuna()
    {
    	


    	
    $.ajax({
        type: 'post',
        url: 'GetRacuniServlet',
        dataType: 'text',
        data: $("#racuniForm").serialize(),
        success: function (data) {
        	var obj = JSON.parse(data);
        	var brojEl = obj.brojEl;
        	var tabla = $("#racuniTable");
        	tabla.html("");
        	if ( $('#katCheck').is(':checked') == false )
        		{
        		tabla.append("<thead> <tr> <th> Datum </th> <th> Zarada tog datuma </th> </tr></thead> <tbody>");
        		for ( var i=0; i< brojEl ; i++) {
	        		tabla.append("<tr> <td> " + obj.kljucevi[i] + "</td> <td> " + obj.pare[i] + "</td> </tr>");
        		}
        		tabla.append("<td colspan=\"2\">Ukupna koliÄina zaraÄenog novca :" + obj.profit );
        		}
        	else
        		{
        		tabla.append("<thead> <tr> <th> Komponenta </th> <th>Koliko je puta prodata </th> <th> KoliÄina novca zaraÄeno na njoj </th> </tr></thead> <tbody>");
        		for ( var i=0; i< brojEl ; i++) {
	        		tabla.append("<tr> <td> " + obj.kljucevi[i] + "</td> <td> " + obj.pare[i].brojPon + "</td><td> " + obj.pare[i].kinta + "</td></tr>");
        		}
        		tabla.append("<td colspan=\"3\">Ukupna koliÄina zaraÄenog novca :" + obj.profit );
        		}

	        		
	        	
	        $("#racuniTable").tablesorter(); 
        	
                        },
        error: function(data,textStatus,errorThrown) {
        	alert("GreÅ¡ka sa generisanjem izveÅ¡taja. ");
        				},
    });
    
    }
    </script>
    
</head>
<body>

<jsp:include page="navigacija.jsp" />
<jsp:include page="loginInfo.jsp" />

<div class="container">
<div class="panel panel-default">
    <form id="racuniForm" class="form-horizontal">
        <fieldset>
            <legend>Unesi vremenski period za koj ŽeliÅ¡ obraÄun :</legend>
            
			<div class="form-group">
                <label for="pocetniDatum" class="col-md-2 control-label">Od :</label>
                <div class="input-group date form_date col-md-5">
					<input type="date" name="pocetniDatum">
                </div>
            </div>
            
			<div class="form-group">
                <label for="krajnjiDatum" class="col-md-2 control-label">Od :</label>
                <div class="input-group date form_date col-md-5">
					<input type="date" name="krajnjiDatum">
                </div>
            </div>
            
          <div class="checkbox">
		    <label>
		      <input type="checkbox" id="katCheck" name="katCheck" onclick="sakrijKategoriju()"> PretraÅ¾i samo jednu kategoriju
		    </label>
		  </div>
		  
		  <!-- Select Basic -->
		<div class="control-group komponente" style="display:none">
		 <label class="control-label" for="selectKategorija" >Kategorija :</label>
		  <div class="controls">
		    <select name="selectKategorija" >
		      <option>Procesori</option>
		      <option>Memorija</option>
		      <option>Oprema za servere</option>
		      <option>OptiÄÂki ureÄaji</option>
		      <option>ZvuÄÂne kartice</option>
		      <option>TV kartice</option>
		      <option>MatiÄne ploÄÂe</option>
		      <option>GrafiÄÂke karte</option>
		      <option>Monitori</option>
		      <option>Hladnjaci i oprema</option>
		      <option>Napajanja</option>
		      <option>KuÄÂiÅ¡ta</option>
		    </select>
		  </div>
		</div>
        </fieldset>
    </form>
    <br>
 </div>   
 
 <button class="btn btn-success" onclick="saljiParametreRacuna()">GeneriÅ¡i spisak raÄuna</button>
 
 <hr>
 <table id="racuniTable" class="col-md-6 tablesorter table" border="1">
 
 </table>
 

</div>


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/jquery.tablesorter.js"></script> 
          <!-- Moja skripta -->
    <script src="webProjekatSkripta.js"></script>
    
</body>
</html>
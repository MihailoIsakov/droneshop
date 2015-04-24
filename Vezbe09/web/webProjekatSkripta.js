// Dakle, ovde dzugaj skriptice koje ce terati po stranici...


function pokaziUredjaje()
{
	$(".komponente").hide();
	$(".kategorije").hide();
	$(".uredjaji").show();
}

function pokaziKomponente()
{
	$(".komponente").show();
	$(".uredjaji").hide();
	$(".kategorije").hide();
	inicijalizuj();

}

function inicijalizuj()
{
	$.ajax({
        type: 'post',
        url: 'InicijalizacijaServlet',
        dataType: 'text',
        success: function (data) {
                        },
        error: function(data,textStatus,errorThrown) {
        				},
    });

}

function pokaziKategorije()
{
	$(".komponente").hide();
	$(".uredjaji").hide();
	$(".kategorije").show();
}

$('#pretraga').on('click', function() {
    $('#kriterijumi').fadeToggle();
    pokaziKomponente();
});


function sendRegistrationPars()
{
	alert($("#regForm").serialize());
	
    $.ajax({
        type: 'post',
        url: 'RegisterServlet',
        dataType: 'text',
        data: $("#regForm").serialize(),
        success: function (data) {
            alert("Uspesno ste se registrovali na nasem WebShopu");
            window.location = "index.jsp";
                        },
        error: function(data,textStatus,errorThrown) {
        	alert("Greska sa registrovanjem ! Korisnicko ime vec postoji !");
        				},
    });
}


function sendLogPars()
{	

	
    $.ajax({
        type: 'post',
        url: 'LogInServlet',
        dataType: 'text',
        data: $("#login").serialize(),
        success: function (data) {
        	var obj = JSON.parse(data);
            alert(obj.message);
            location.reload();
            korisnikLog();
                        },
        error: function(data) {

        				},
    });
}

function korisnikLog()
{
		$(".userRights").show();
        $(".adminRights").hide();
}

function sendAdminLogPars()
{	
	
    $.ajax({
        type: 'post',
        url: 'LogInServlet',
        dataType: 'text',
        data: $("#adminForm").serialize(),
        success: function (data) {
        	var obj = JSON.parse(data);
            alert(obj.message);
            window.location = "http://localhost:8080/WebProjekat/index.jsp";
            $(".userRights").hide();
            $(".adminRights").show();
                        },
        error: function(data,textStatus,errorThrown) {
        	var obj = JSON.parse(data);
            alert(obj.message);
        }
    });
}

function sendQueryPars()
{
	//alert($("#queryPars").serialize());

		$.ajax({
	        type: 'post',
	        url: 'PretragaServlet',
	        dataType: 'text',
	        data: $("#queryPars").serialize() ,
	        success: function (data) {
	        	var obj = JSON.parse(data);
	        	var brojEl = obj.brojEl;
	        	var divZaCont = $("#content");
	        	divZaCont.html("");
	        	
	        	if ($("#radios-0").is(':checked'))
	        	{
	        	divZaCont.append("<b> Komponente : </b> <hr>");
	        	for ( var i=0; i< brojEl ; i++) {
	        		divZaCont.append("<div class=\"list-group-item\" margin=\"15px\" > <img src=\"images/" +
	        				obj.niz[i].slika +
	        					" \"  height=\"120\" width=\"120\" align=\"left\" margin=\"10px\"> <b>" +
	        						obj.niz[i].naziv + "</b><br>Cena: "+
	        							obj.niz[i].cena + "din <br>Raspoloživa količina: " +
	        								obj.niz[i].raspolozivaKolicina + "<br>Kategorija: " +
	        									obj.niz[i].kategorija.naziv + "<br><p>"+obj.niz[i].opis  +
	        							"</p>");
	        								if (obj.admin == "true")
	        									divZaCont.append("<p style=\"text-align : right\"> <button type=\"button\" class=\"btn btn-default btn-xs\" onclick=\"izmeniEntitet('"+
	        											obj.niz[i].naziv + "')\">Izmeni proizvod</button>" +
	        														"<button type=\"button\" class=\"btn btn-default btn-xs\" onclick=\"ukloniIzMape('"+
	        															obj.niz[i].naziv + "')\">Obriši proizvod</button> </p>");
	        								if (obj.korisnik == "true")
	        									divZaCont.append("<p style=\"text-align : right\"> <button onclick=\"dodajUKorpu('"+ obj.niz[i].naziv + "')\" type=\"button\" class=\"btn btn-default btn-xs\">Dodaj u korpu</button></p>");
	        		divZaCont.append("</div>");
	        		}
	        	}
	        	else
	        		{
	        		divZaCont.append("<b> Uređaji : </b> <hr>");
	        		for ( var i=0; i< brojEl ; i++) {
	        			var spisak = "";
	        			for ( var j=0; j< obj.niz[i].brojKomponenti ; j++)
	        			spisak += obj.niz[i].spisakKomponenti[j] + " <br> " ;
	        			divZaCont.append("<div class=\"list-group-item\" margin=\"15px\" >" +
	        				"<b> Naziv : </b>" + obj.niz[i].naziv + "<br>" +
	        					"<b> Cena : </b>" + obj.niz[i].cena + "<br>" +
	        						"<b> Opis : </b>" + obj.niz[i].opis + "<br>" +
	        							"<b> Komponente : </b> " + spisak  );
						if (obj.admin == "true")
							divZaCont.append("<p style=\"text-align : right\"> <button type=\"button\" class=\"btn btn-default btn-xs\" onclick=\"izmeniEntitet('"+
									obj.niz[i].naziv + "')\">Izmeni proizvod</button>" +
												"<button type=\"button\" class=\"btn btn-default btn-xs\" onclick=\"ukloniIzMape('"+
													obj.niz[i].naziv + "')\">Obriši proizvod</button> </p>");
						if (obj.korisnik == "true")
							divZaCont.append("<p style=\"text-align : right\"> <button onclick=\"dodajUKorpu('"+ obj.niz[i].naziv + "')\" type=\"button\" class=\"btn btn-default btn-xs\">Dodaj u korpu</button></p>");
	        			divZaCont.append("</div>");
	        		}
	        		
	        		
	        		
	        		}
	        
	        	//alert(obj.niz[0].kategorija.naziv);
	                        },
	        error: function(data) {
	        	alert("Greska sa query parametrima");
	        }
	    });



}

function odjaviMe()
{
	var sendData = { logoff : true };
	
	$.ajax({
        type: 'post',
        url: 'LogInServlet',
        dataType: 'JSON',
        data: sendData ,
        success: function (data) {
        	location.reload();
                        },
        error: function(data) {
        	alert("Greska prilikom odjavljivanja");
        	
        }
    });
}

function posaljiPoruku()
{
	$.ajax({
        type: 'post',
        url: 'OperaterskaPorukaServlet',
        dataType: 'text',
        data: $("#poruka-form").serialize() ,
        success: function (data) {
        	$('#contact').modal('hide');
                        },
        error: function(data) {
        	alert("Greska prilikom odjavljivanja");
        	
        }
    });
}

function dodajUKorpu(stringic)
{
	var sendData = { 'naziv' : stringic };
	
	$.ajax({
        type: 'post',
        url: 'AddToCartServlet',
        dataType: 'text',
        data: sendData ,
        success: function (data) {
        	alert("Predmet uspešno dodat u korpu.");
                        },
        error: function(data) {
        	alert("Greska prilikom dodavanja predmeta u korpu.");
        	
        }
    });

}

function sendNewEntityPars()
{
	alert($("#newEntityForm").serialize());
	
	$.ajax({
        type: 'post',
        url: 'NewEntityServlet',
        dataType: 'text',
        data: $("#newEntityForm").serialize() ,
        success: function (data) {
        	alert("Entitet uspešno kreiran.");
                        },
        error: function(data) {
        	alert("Greska prilikom kreiranja novog entiteta.");
        	
        }
    });

}

function ukloniIzKorpe(string)
{
	var sendData = { 'naziv' : string };
	
	$.ajax({
        type: 'post',
        url: 'ObrisiIzKorpeServlet',
        data: sendData ,
        success: function (data) {
        	alert("Predmet uspešno obrisan iz korpe.");
        	location.reload();
                        },
        error: function(data) {
        	alert("Greška prilikom brisanja predmeta iz korpe.");
        	
        }
    });
}

function kreirajRacun()
{

	$.ajax({
        type: 'post',
        url: 'GenerisiRacunServlet',
        dataType: 'text',
        success: function (data) {
        	var odgovor = JSON.parse(data);
        	alert(odgovor.poruka );
        	location.reload();
                        },
        error: function(data) {
        	alert("Greška prilikom kupovine artikala. ");
        	
        }
    });


}

function ukloniIzMape(string)
{
	var sendData = { 'naziv' : string };
	
	$.ajax({
        type: 'post',
        url: 'DeleteEntityServlet',
        data: sendData ,
        success: function (data) {
        	alert("Predmet uspešno obrisan iz baze.");
        	location.reload();
                        },
        error: function(data) {
        	alert("Greška prilikom brisanja predmeta iz baze.");
        	
        }
    });
}

function izmeniEntitet(string)
{
	var sendData = { 'naziv' : string };
	
	$.ajax({
        type: 'post',
        url: 'EditEntityServlet',
        data: sendData ,
        success: function (data) {
        	window.location = "izmenaEntiteta.jsp";
                        },
        error: function(data) {
        	alert("Greška prilikom izmene predmeta iz baze.");
        	
        }
    });
}

function sendEditedComponent()
{
	$('#komponenteUredjaja option').prop('selected', true);
	alert($("#changedEntityForm").serialize());
	
	$.ajax({
        type: 'post',
        url: 'SaveEditedEntityServlet',
        data: $("#changedEntityForm").serialize() ,
        success: function (data) {
        	alert("Uspešno izmenjen entitet.");
        	window.location = "index.jsp";
                        },
        error: function(data) {
        	alert("Greška prilikom izmene predmeta iz baze.");
        	
        }
    });
}







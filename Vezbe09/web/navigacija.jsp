<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
      <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    
</head>
<body>

  <div class="navbar navbar-default navbar-static-top">
   	<div class="container" >
   		
   		<a href ="index.jsp" class="navbar-brand glyphicon glyphicon-home">WebShop</a>
   		
      <button class="navbar-toggle" data-target=".navHeaderCollapse" data-toggle="collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>      
      </button>

   			<div class="collapse navbar-collapse navHeaderCollapse">
	   			<ul class="nav navbar-nav navbar-right">
	   			<c:if test="${ imaNekoga }">
	   				<c:if test="${ user.admin }">
	   				<li> <a href="UnosNovihEntiteta.jsp">Unos novih entiteta</a></li>
	   				<li> <a href="Izvestavanje.jsp">Izveštavanje</a></li>
	   				</c:if>
	   			</c:if>
	   			<c:if test="${ imaNekoga }">
	   				<c:if test="${ !user.admin }">
	   				<li> <a href="sastavljanjeUredjaja.jsp">Sastavljanje uređaja</a></li>
	   				<li> <a href="cartPrikaz.jsp" class="glyphicon glyphicon-shopping-cart">Moja kolica</a>
	   				<li> <a href="#contact" data-toggle="modal"> Kontakt </a> </li>
	   				</c:if>
	   			</c:if>

	   			</ul>
   			</div>
   	</div>
  </div>
  
</body>
</html>
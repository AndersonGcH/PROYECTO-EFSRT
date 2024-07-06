<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menu</title>
<link href="css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
<style>
	body{
		background-color:#F5F5F5;
		font-size: 13px;
	}
	.btn {
	    font-size: 13px; 
	}
	.dates{
		color: black;
	}
	.nav-link {
        transition: background-color 0.3s, color 0.3s, border 0.3s;
        padding: 15px;
        display: block;
        border-radius: 5px; 
    }
    .nav-link:hover {
        background-color: rgba(27, 51, 83, 0.3); 
        color: #333; 
        cursor:pointer;
        border-radius: 5px; 
    }
	.offcanvas {
        position: fixed;
        top: 0;
        bottom: 0;
        left: -150px; 
        max-width: 330px;
        background-color:#F5F5F5;
        transition: all 0.7s ease;
        font-size: 15px; 
        z-index: 1050; 
    }
    .offcanvas.show {
        left: 0; 
    }
    .offcanvas-header {
        padding: 1rem 1rem 0.5rem 0.5rem;
    }
    .offcanvas-body {
        padding: 0.4rem;
        margin: 5px 15px; 
    }
    .logout-link {
        position: absolute; 
        bottom: 25px; 
        width: 80%; 
    }
</style>
</head>
<body>
	<nav class="navbar" style="background-color: rgba(27, 51, 83, 0.9);">
		<div class="container-fluid" style="width=200px;">
			<div class="navbar-toggler" style="margin: 0px;" data-bs-toggle="offcanvas" data-bs-target="#offcanvasDarkNavbar" aria-controls="offcanvasDarkNavbar">
			    <img src="img/logo-hotel-angostura.png" alt="Abrir menú" style="width: 95px; height: auto; cursor: pointer;">
			</div>
	        <div class="offcanvas offcanvas-start text-bg-light" tabindex="-1" id="offcanvasDarkNavbar" aria-labelledby="offcanvasDarkNavbarLabel">
	      		<div class="offcanvas-header">
	        		<h5 class="offcanvas-title" id="offcanvasDarkNavbarLabel"></h5>
	        		<button type="button" class="btn-close btn-close-dark" data-bs-dismiss="offcanvas" aria-label="Close"></button>
	      		</div>
	      		<div class="offcanvas-body">
		      		<div class="user-info" style="text-align: center;">
					    <img style="width:50%; border-radius:50%;" src="${sessionScope.FOTO}">
					    <h4 style="margin:20px 0px 25px 0px;" class="dates">${sessionScope.DATOS}</h4>
					</div>	<hr>
	        		<ul class="navbar-nav justify-content-end flex-grow-1 pe-2">
			       <!-- Bucle para realizar recorrido sobre el atributo de tipo sesion MENUS-->
				       <c:forEach items="${sessionScope.MENUS}" var="row">  
				          <li class="nav-item">
				            <a class="nav-link text-dark mouse" href="${row.ruta}">${sessionScope.ICONO}${row.descripcion}</a>
				          </li>
					   </c:forEach>       
	        	   </ul>
	        	   <a class="nav-link text-dark mouse logout-link" href="ServletUsuario?accion=CERRARSESION">
	        	   		<i class='bx bx-log-out' style="width:40px;"></i>
	        	   		<span>Cerrar Sesión</span>
	        	   </a>
	      	   </div>
	    	</div>
		</div>
	</nav>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
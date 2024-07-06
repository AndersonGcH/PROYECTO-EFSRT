<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Iniciar sesión</title>
	<link href="css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
	<style>
		body, html {
            height: 100%;
            margin: 0;
            overflow: hidden;
            font-size: 14px; 
        }
        .image-background {
            background-image: url('img/fondo-hotel-angostura.jpg');
            filter: brightness(0.7); 
            background-size: cover;
            background-position: center;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: -1;
        }
        .container {
            text-align: center;
            position: relative;
            z-index: 1;
        }
        .form-container {
            background-color: white;
            padding: 2rem;
            border-radius: 15px;
            width: 100%;
            max-width: 300px; 
            margin: 0 auto; 
        }
        .logo {
        	margin-top: 60px;
            margin-bottom: 40px;
            text-align: center;
        }
        .logo img {
            width: 150px; 
            height: auto;
        }
        h3 {
            margin-bottom: 1.2rem; 
        }
      	.form-label{
      		text-align: left; 
      		margin-bottom: 1rem;
      	}
        .form-control {
            margin-bottom: 1rem; 
        }
        .btn {
            margin-top: 1rem; 
            margin-bottom: 0.3rem;
        }
        .password-wrapper {
		    position: relative;
		}
		.password-wrapper .toggle-password {
		    position: absolute;
		    top: 50%;
		    right: 10px;
		    transform: translateY(-50%);
		    cursor: pointer;
		    color: #6c757d;
		}
		.password-wrapper .toggle-password:hover {
		    color: #495057; 
		}  
	</style>
</head>
<body>
	<div class="image-background"></div>
	<div class="container">
	 	<div class="row justify-content-center">
 			<div class="col-md-6">
 				<div class="logo">
	       			<img class="" alt="logo" src="img/logo-hotel-angostura.png">
	    		</div>
	    		<div class="form-container">
      				<c:if test="${sessionScope.TERMINADA!=null}">
		      			<div class="alert alert-success" role="alert">
				  			${sessionScope.TERMINADA}
			  			</div>
			 		</c:if>	
					<c:if test="${sessionScope.CERRAR!=null}">
		      			<div class="alert alert-danger" role="alert">
				 			${sessionScope.CERRAR}
			  			</div>
			  		</c:if> 
			  		<c:remove var="TERMINADA" scope="session"/>
		 			<c:remove var="CERRAR" scope="session"/>
	     			<h3 class="text-center">Inicie sesión</h3>
	     			<!-- Login -->
			     	<form method="post" action="ServletUsuario?accion=INICIARSESION">
						<div class="mb-2 text-start">
						    <label for="username" class="form-label">Usuario</label>
						    <input type="text" class="form-control" id="user" name="username">
						</div>
						<div class="mb-2 text-start">
						    <label for="password" class="form-label">Contraseña</label>
						    <div class="password-wrapper">
						    	<input type="password" class="form-control" id="pass" name="password">
								<i class='bx bx-show-alt toggle-password'></i>
							</div>
						</div>
						  	<button type="submit" class="btn btn-dark w-100">Iniciar sesión</button>
					</form>
	    		</div>
  			</div>
		</div>
	</div>
	<script>
		const pass = document.getElementById("pass"),
			  icon = document.querySelector(".bx");
		
		icon.addEventListener("click", e => {
			if (pass.type === "password"){
				pass.type = "text";
				icon.classList.remove("bx-show-alt")
				icon.classList.add("bx-hide")
			} else{
				pass.type = "password";
				icon.classList.add("bx-show-alt")
				icon.classList.remove("bx-hide")
			}
		})	
	</script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
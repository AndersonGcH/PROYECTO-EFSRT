<%
HttpSession sesion = request.getSession(false);
if(sesion == null || session.getAttribute("DATOS") == null) {
    response.sendRedirect("Login.jsp");
}
%>
<jsp:include page="Menu.jsp"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Menu principal</title>
	<link href="css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<style>
		body, html {
            height: 100%;
            margin: 0;
            overflow: hidden;
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
<div class="image-background"></div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
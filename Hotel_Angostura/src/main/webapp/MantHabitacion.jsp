<%
HttpSession sesion = request.getSession(false);
if(sesion == null || session.getAttribute("DATOS") == null) {
    response.sendRedirect("Login.jsp");
}
%>
<jsp:include page="Menu.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Mantenimiento de habitación</title>
		<link href="css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
		<link href="https://cdn.datatables.net/2.0.3/css/dataTables.bootstrap5.css" rel="stylesheet">
		<link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" rel="stylesheet">
		<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
		<style>
			body{
				background-color:#F5F5F5;
				font-size: 13px;
			}
			.btn {
	        	font-size: 13px; 
	    	}
			.container{
				margin-top: 35px;
			}
			.ocultar-caja {
				display: none;
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
			.dt-paging-button.page-item a.page-link {
				border: none; 
				border-radius: 50%;
				color: #000;
				width: 30px;
				height: 30px;
				display: flex;
				font-size:14px;
				align-items: center;
				justify-content: center;
				padding: 0;
				margin: 2px;
				transition: background-color 0.3s, color 0.3s;
			}
			.dt-paging-button.page-item.active a.page-link {
				background-color: #000; 
				color: #fff; 
			}
			.dt-paging-button.page-item a.page-link:hover {
				background-color: rgba(0, 0, 0, 0.8); 
				color: #fff; 
			}
			.btn-editar:hover, .btn-eliminar:hover {
	        	background-color: rgba(128, 128, 128, 0.3); 
	    	}
			.help-block {
				color: red;
			}
			.form-group.has-error .form-control-label {
				color: red;
			}
			.form-group.has-error .form-control {
				border: 1px solid red;
				box-shadow: 0 0 0 0.2rem rgba(250, 16, 0, 0.18);
			}
			.form-group.has-error .form-select {
				border: 1px solid red;
				box-shadow: 0 0 0 0.2rem rgba(250, 16, 0, 0.18);
			}
			label.form-label, input.form-control,select.form-select,button.btn {
	        	font-size: 13px; 
	    	}
		</style>
	</head>
	<body>
		<div class="container">
			<!-- Content here -->
			<h3 class="text-center mb-3">Mantenimiento de Habitaciones</h3>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-dark mb-2" data-bs-toggle="modal" data-bs-target="#modalMantenimientoHabitacion">Agregar nueva habitación</button>
			<!-- Table -->
			<table id="tableMantenimientoHabitacion" class="table table-light" style="width:100%;">
		        <thead>
		            <tr>
		                <th style="text-align:left;">Código</th>
		                <th style="text-align:left;">Nro habitación</th>
		                <th>Tipo habitación</th>
		                <th>Descripción</th>
		                <th>Precio</th>
		                <th style="width: 12%;"></th>
		            </tr>
		        </thead>
		        <tbody>
		       
		        </tbody>
		    </table>
			<!-- Modal Añadir-->
			<div class="modal fade" id="modalMantenimientoHabitacion" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
			  <div class="modal-dialog modal-dialog-centered">
			    <div class="modal-content">
			     <div class="modal-header">
		        	<h5 class="modal-title">Habitaciones</h5>
		          </div>
			      <div class="modal-body">
			        <form id="frmMantenimientoHabitacion" method="post" action="ServletMantHabitacion?accion=insertar">
					  <div class="form-group ocultar-caja">
					    <label for="exampleInputEmail1" class="form-label">ID Habitación</label>
					    <input type="text" class="form-control" name="codigo" id="id-codigo" value="0" readonly>
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Nro habitación</label>
					    <input type="text" class="form-control" name="numero" id="id-numero">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Tipo Habitación</label>
					    <select class="form-select" name="tipo" id="id-tipo">
						  <option value="" selected>Seleccione Tipo Habitación</option>
						</select>
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Descripción</label>
					    <input type="text" class="form-control" name="descripcion" id="id-descripcion">
					  </div>
					  <div class="form-group">
					    <label for="exampleInputEmail1" class="form-label">Precio</label>
					    <input type="text" class="form-control" name="precio" id="id-precio">
					  </div>

					  <div class="modal-footer">
				        <button type="submit" class="btn btn-dark" style="width:48%">Guardar</button>
				        <button type="button" class="btn btn-secondary" style="width:48%" data-bs-dismiss="modal" id="btn-cancelar">Cancelar</button> 
				      </div>
					</form>
			      </div>
			    </div>
			  </div>
			</div>
			<form id="form-eliminar" method="post" action="ServletMantHabitacion?accion=eliminar">
				<input type="hidden" name="codigo" id="codigoEliminar">
			</form>
		</div>
		<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
		<script src="https://cdn.datatables.net/2.0.3/js/dataTables.js"></script>
		<script src="https://cdn.datatables.net/2.0.3/js/dataTables.bootstrap5.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
		<!--  Libreria para validar -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.0/js/bootstrapValidator.js"></script>
		<!-- Sweet alert -->
		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
		<!-- Toastr -->
		<script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
		<c:if test="${sessionScope.MENSAJE!=null}">
			<script>
				toastr.success("${sessionScope.MENSAJE}", "Mensaje", {timeOut:1000})
			</script>
		</c:if>
		<c:remove var="MENSAJE" scope="session"/>
		<script>
			llenarTabla();
			llenarTipo();
			
			$(document).on("click",".btn-eliminar",function(){
				let cod;
				cod=$(this).parents("tr").find("td")[0].innerHTML;
				$("#codigoEliminar").val(cod);
				
				Swal.fire({
					  title: "¿Seguro de eliminar?",
					  text: "",
					  icon: "warning",
					  showCancelButton: true,
					  confirmButtonColor: "#DC3545", // Danger
					  cancelButtonColor: "#6C757D",  // Secondary
					  confirmButtonText: "Si, estoy seguro",
					  cancelButtonText: "No",
					  customClass: {
			              popup: 'small-swal', 
			            },
			          width: '400px', 
			          heightAuto: false, 
					}).then((result) => {
					  if (result.isConfirmed) {
					    $("#form-eliminar").submit();
					  }
					});
			})
			
			$(document).on("click","#btn-cancelar",function(){
				$("#frmMantenimientoHabitacion").trigger("reset");
				$("#frmMantenimientoHabitacion").bootstrapValidator("resetForm",true);
			})
			
			function llenarTabla(){
				$.get("ServletMantHabitacion?accion=listar",function(response){
					$.each(response,function(index,item){
						let precioSoles = 'S/ ' + parseFloat(item.precio).toFixed(2);
						$("#tableMantenimientoHabitacion tbody").append(`<tr>
						<td style="vertical-align: middle; text-align:left;">\${item.codigoHabi}</td>
						<td style="vertical-align: middle; text-align:left;">\${item.numeroHabi}</td>
						<td style="vertical-align: middle;">\${item.nombreTipo}</td>
						<td style="vertical-align: middle;">\${item.descripcion}</td>
						<td style="vertical-align: middle;">\${precioSoles}</td>
						<td>
						<button type="button" class="btn btn-editar ms-1" style="border-radius:50%;padding: 5px 10px;" data-bs-toggle="modal" data-bs-target="#modalMantenimientoHabitacion">
	                	<i class='bx bxs-pencil'></i>
	                	</button>
	                	<button type="button" class="btn btn-eliminar ms-1" style="border-radius:50%;padding: 5px 10px;">
	                	<i class='bx bxs-trash-alt'></i>
	                	</button>
						</td>
						</tr>`);
					})
					new DataTable("#tableMantenimientoHabitacion",{
						language: {
	                        url: "json/es-ES.json"
	                    },
	                    "lengthMenu": [5, 10, 25, 50, 100], 
			            "pageLength": 5 
					});
				})
			}
			
			$(document).on("click",".btn-editar",function(){
				let cod;
				cod=$(this).parents("tr").find("td")[0].innerHTML;
				$.get("ServletMantHabitacion?accion=buscarPorCodigo&codigo="+cod,function(data){
					$("#id-codigo").val(data.codigoHabi);
					$("#id-numero").val(data.numeroHabi);
					$("#id-tipo").val(data.codigoTipo);
					$("#id-descripcion").val(data.descripcion);
					$("#id-precio").val(data.precio);
				})
			})
			
			function llenarTipo(){
				$.get("ServletTipoHabitaciones",function(data){
					$.each(data,function(index,item){
						$("#id-tipo").append(`<option value="\${item.codigoTipo}">\${item.nombreTipo}</option>`)
					})
				})
			}
		</script>
	</body>
</html>
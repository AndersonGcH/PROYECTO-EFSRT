<%
HttpSession sesion = request.getSession(false);
if(sesion == null || session.getAttribute("DATOS") == null) {
    response.sendRedirect("Login.jsp");
}
%>
<jsp:include page="Menu.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Registro Habitacion</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
	<link href="https://cdn.datatables.net/2.0.3/css/dataTables.bootstrap5.css" rel="stylesheet">
	<link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" rel="stylesheet">
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
		.btn-editar:hover, .btn-boleta:hover, .btn-eliminar:hover {
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
			<h3 class="text-center mb-3">Reserva de Habitaciones</h3>
			<button type="submit" class="btn btn-dark mb-2" data-bs-toggle="modal" data-bs-target="#modalRegistros">Reservar habitación</button>
<a class="btn btn-dark mb-2" href="https://drive.google.com/uc?export=download&id=1eoHRt9HYI8aT2QLogO9pdMwBHn8Jpyot" role="button">Catalago de Habitaciones</a>			<!-- Table -->
			<table id="tableRegistro" class="table table-light" style="width:100%">
		        <thead>
		            <tr >
		           		<th>Código</th>
		           		<th>Nombres</th>
		                <th>Apellidos</th>
		           		<th>DNI</th>
		                <th>Teléfono</th>
		                <th>F. Ingreso</th>
		                <th>F. Salida</th>
		               	<th>Tipo</th>
		                <th>Nro.</th>
		                <th style="text-align:center;">Estado</th>
		                <th style="width: 13%;"></th>
		            </tr>
		        </thead>
		        <tbody>
		       
		        </tbody>
		    </table>
			<!-- Modal -->
			<div class="modal fade" id="modalRegistros" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
			  <div class="modal-dialog modal-dialog-centered">
			    <div class="modal-content">
			     <div class="modal-header">
		        	<h5 class="modal-title">Habitaciones</h5>
		           </div>
			      <div class="modal-body">
			        <form id="frmRegistros" class="row g-3" method="post" action="ServletReservaHabitacion?accion=insertar">
					  <div class="form-group ocultar-caja">
					    <label for="exampleInputEmail1" class="form-label">Código de reserva</label>
					    <input type="text" class="form-control" name="codigo" value="0" id="id-codigo" readonly>
					  </div>
					  <div class="form-group col-md-6">
					    <label for="exampleInputEmail1" class="form-label">Nombres</label>
					    <input type="text" class="form-control" name="nombres" id="id-nombres">
					  </div>
					  <div class="form-group col-md-6">
					    <label for="exampleInputEmail1" class="form-label">Apellidos</label>
					    <input type="text" class="form-control" name="apellidos" id="id-apellidos">
					  </div>
					  <div class="form-group col-md-6">
					    <label for="exampleInputEmail1" class="form-label">DNI</label>
					    <input type="text" class="form-control" name="dni" id="id-dni">
					  </div>
					  <div class="form-group col-md-6">
					    <label for="exampleInputEmail1" class="form-label">Teléfono</label>
					    <input type="text" class="form-control" name="telefono" id="id-telefono">
					  </div>
					  <div class="form-group col-md-6">
					    <label for="exampleInputEmail1" class="form-label">Fecha de ingreso</label>
					    <input type="date" class="form-control" name="fechaIngreso" id="id-fechaIngreso">
					  </div>
					  <div class="form-group col-md-6">
					    <label for="exampleInputEmail1" class="form-label">Fecha de salida</label>
					    <input type="date" class="form-control" name="fechaSalida" id="id-fechaSalida">
					  </div>
					  <div class="form-group col-md-4">
					    <label for="exampleInputEmail1" class="form-label">Tipo de habitación</label>
					    <select class="form-select" name="tipo" id="id-tipo">
						  <option value="" selected>Seleccione Tipo</option>
						</select>
					  </div>
					  <div class="form-group col-md-4">
					    <label for="id-nro" class="form-label">Número de habitación</label>
					    <select class="form-select" name="nro" id="id-nro">
						  <option value="" selected>Seleccione Nro</option>
						</select>
					  </div>
					  <div class="form-group col-md-4">
					    <label for="exampleInputEmail1" class="form-label">Estado</label>
					    <select class="form-select" name="estado" id="id-estado">
						  <option value="" selected>Seleccione Estado</option>
						  <option value="Pendiente">Pendiente</option>
						  <option value="Reservado">Reservado</option>
						</select>
					  </div>
					  <div class="modal-footer">
				        <button type="submit" class="btn btn-dark" style="width:48%">Grabar</button>
				      <button type="button" class="btn btn-secondary" style="width:48%" data-bs-dismiss="modal" id="btn-cerrar">Cerrar</button>
				      </div>
					</form>
			      </div>
			    </div>
			  </div>
			</div>
			<form id="form-eliminar" method="post" action="ServletReservaHabitacion?accion=eliminar">
				<input type="hidden" name="codigo" id="codigoEliminar">
			</form>
			 <form id="form-boleta" method="post" action="ServletReservaHabitacion?accion=reporte">
					<input type="hidden" name="codigo" id="codigoBoleta">
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
		
		<!-- TOASTR -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
			<c:if test="${sessionScope.MENSAJE!=null}">
				<script>
					toastr.success("${sessionScope.MENSAJE}", "Mensaje", {timeOut:1000})
				</script>
			</c:if>	
			<c:remove var="MENSAJE" scope="session"/>	
      <script>
			llenarTabla();
			llenarTipo();
			function llenarTabla(){
				$.get("ServletReservaHabitacion?accion=listar",function(e){
					$.each(e,function(index,item){
						$("#tableRegistro").append(`<tr>
						<td style="vertical-align: middle;text-align:left;">\${item.codigoReserva}</td>
						<td style="vertical-align: middle;">\${item.nombres}</td>
						<td style="vertical-align: middle;">\${item.apellidos}</td>
						<td style="vertical-align: middle;">\${item.dni}</td>
						<td style="vertical-align: middle;">\${item.telefono}</td>
						<td style="vertical-align: middle;">\${item.fIngreso}</td>
						<td style="vertical-align: middle;">\${item.fSalida}</td>
						<td style="vertical-align: middle;">\${item.nombreTipo}</td>
						<td style="vertical-align: middle;text-align:center;">\${item.nroHabitacion}</td>
						<td style="vertical-align: middle;">\${item.estado}</td>
						<td>
	                	<button type="button" class="btn btn-editar ms-1" style="border-radius:50%;padding: 5px 10px;" data-bs-toggle="modal" data-bs-target="#modalRegistros">
	                	<i class='bx bxs-pencil'></i>
	                	</button>
	                	<button type="button" class="btn btn-boleta ms-1" style="border-radius:50%;padding: 5px 10px;">
	                	<i class='bx bxs-download'></i>
	                	</button>
	                	<button type="button" class="btn btn-eliminar ms-1" style="border-radius:50%;padding: 5px 10px;">
	                	<i class='bx bxs-trash-alt'></i>
	                	</button>
                	    </td>
						</tr>`);
					})
					new DataTable("#tableRegistro",{
						language: {
	                        url: "json/es-ES.json"
	                    },
	                    "lengthMenu": [5, 10, 25, 50, 100], 
			            "pageLength": 5 
					});
				})
			}
			
			$(document).on("click",".btn-editar",function(){
				let cod=$(this).parents("tr").find("td")[0].innerHTML;
				$.get("ServletReservaHabitacion?accion=buscarPorCodigo&codigo="+cod,function(data){
					$("#id-codigo").val(data.codigoReserva);
					$("#id-nombres").val(data.nombres);
					$("#id-apellidos").val(data.apellidos);
					$("#id-dni").val(data.dni);
					$("#id-telefono").val(data.telefono);
					$("#id-fechaIngreso").val(data.fIngreso);
					$("#id-fechaSalida").val(data.fSalida);
					$("#id-tipo").val(data.codigoTipo);
					$("#id-nro").val(data.codigoHabi);
					$("#id-estado").val(data.estado);
				})
			})
				
			$(document).on("click","#btn-cerrar",function(){
				$("#frmRegistros").trigger("reset");
				$("#frmRegistros").bootstrapValidator("resetForm",true);
			})
	
			
			function llenarTipo(){
				$.get("ServletTipoHabitaciones",function(data){
					$.each(data,function(index,item){
						$("#id-tipo").append(`<option value="\${item.codigoTipo}">\${item.nombreTipo}</option>`)
					})
				})
			}
			
			// Evento de cambio en el select de tipos
			$("#id-tipo").on("change", function () {
			    const codigoTipo = $(this).val();

			    // Realiza una llamada AJAX al servlet para obtener las habitaciones filtradas
			    $.get("ServletReservaHabitacion?accion=filtro", { codigo: codigoTipo }, function (data) {
			        // Verifica si hay habitaciones disponibles
			        if (data.length > 0) {
			            // Actualiza el select de números de habitación con las opciones recibidas
			            $("#id-nro").empty();
			            $.each(data, function (index, item) {
			                $("#id-nro").append(`<option value="\${item.codigoHabi}">\${item.nroHabitacion}</option>`);
			            });
			        } else {
			            // Si no hay habitaciones disponibles, muestra un mensaje o deshabilita el select
			            $("#id-nro").empty().append('<option value="">No hay habitaciones disponibles</option>');
			        }
			    });
			});


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
			
			$(document).on("click",".btn-boleta",function(){
				let cod;
				cod=$(this).parents("tr").find("td")[0].innerHTML;
				$("#codigoBoleta").val(cod);
				
				Swal.fire({
					  title: "¿Generar Boleta?",
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
					    $("#form-boleta").submit();
					  }
					});
			})
			
			 $(document).ready(function(){     
			        $('#frmRegistros').bootstrapValidator({      
			        	 fields:{
			        		nombres:{
			        			validators:{
			        				notEmpty:{
			        					message:'Ingresar nombre'
			        				},
			        				regexp:{
			        					regexp:/^[a-zA-Z\s\ñ\Ñ\á\é\í\ó\ú\Á\É\Í\Ó\Ú]{3,30}$/,
			        					message:'Ingresar nombre solo letras MIN:3 MAX: 30'
			        				}
			        			}
			        		},
			        		apellidos:{
			        			validators:{
			        				notEmpty:{
			        					message:'Ingresar apellido'
			        				},
			        				regexp:{
			        					regexp:/^[a-zA-Z\s\ñ\Ñ\á\é\í\ó\ú\Á\É\Í\Ó\Ú]{2,30}$/,
			        					message:'Ingresar apellido solo letras MIN:2 MAX: 30'
			        				}
			        			}
			        		},
			        		cantidad:{
			        			validators:{
			        				notEmpty:{
			        					message:'Ingresar cantidad'
			        				},
			        				regexp:{
			        					regexp:/^(\d|[1][0-5])$/,
			        					message:'Ingresar cantidad solo digitos MIN:0 MAX: 15'
			        				}
			        			}
			        		},
			        		dni:{
			        			validators:{
			        				notEmpty:{
			        					message:'Ingresar dni'
			        				},
			        				regexp:{
			        					regexp:/^\d{8}$/,
			        					message:'Ingresar dni de 8 digitos'
			        				}
			        			}
			        		},
			        		telefono:{
			        			validators:{
			        				notEmpty:{
			        					message:'Ingresar telefono'
			        				},
			        				regexp:{
			        					regexp:/^9\d{8}$/,
			        					message:'Ingresar telefono de 9 digitos'
			        				}
			        			}
			        		},
			        		tipo:{
			        			validators:{
			        				notEmpty:{
			        					message:'Ingresar tipo'
			        				}
			        			}
			        		} 
			        		 
			        	 }
			        });   
						
			    });    
		</script>
</body>
</html>
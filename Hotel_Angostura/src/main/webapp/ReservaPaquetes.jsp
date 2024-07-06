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
	<title>Reserva de Paquetes Turísticos</title>
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
			<h3 class="text-center mb-3">Reserva de Paquetes Turísticos</h3>
			<button type="submit" class="btn btn-dark mb-2" data-bs-toggle="modal" data-bs-target="#modalReservaPaq">Reservar Paquete Turístico</button>
<a class="btn btn-dark mb-2" href=" https://drive.google.com/uc?export=download&id=1F3NEX_hfE9VegFUaJnQuRteX-Ir_4D0s" role="button">Catalago de Paquetes</a>			<!-- Table -->
			<table id="tableReservaPaq" class="table table-light" style="width:100%">
		        <thead>
		            <tr>
		                <th style="width: 8%;">Código</th>
	                    <th style="width: 12%;">Nombre</th>
	                    <th style="width: 14%;">Apellido</th>
	                    <th style="width: 5%;">DNI</th>
	                    <th style="width: 8%;">Teléfono</th>
	                    <th style="width: 10%;">Fecha</th>
	                    <th style="width: 14%;">Paquete</th>
	                    <th style="width: 12%;">Precio</th>
	                    <th style="width: 5%;">Cant.</th>
	                    <th style="width: 17%;">Total</th>
	                    <th hidden>COD PAQUETE</th>
	                    <th style="width: 15%;"></th> 
		            </tr>
		        </thead>
		        <tbody>
		       
		        </tbody>
		    </table>
		    
			<!-- Modal -->
			<div class="modal fade" id="modalReservaPaq" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
			  <div class="modal-dialog modal-dialog-centered">
			    <div class="modal-content">
			     <div class="modal-header">
		        	<h5 class="modal-title">Paquetes Turisticos</h5>
		          </div>
			      <div class="modal-body">
			        <form class="row g-3" id="frmReserva" method="post" action="ServletReservaPaquetes?accion=insertar">
					  <div class="form-group ocultar-caja">
					    <label for="exampleInputEmail1" class="form-label">COD-RESERVA</label>
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
					    <label for="exampleInputEmail1" class="form-label">Fecha</label>
					    <input type="date" class="form-control" name="fecha" id="id-fecha">
					  </div>
					  <div class="form-group col-md-6">
					    <label for="exampleInputEmail1" class="form-label">Paquete</label>
					    <select class="form-select" name="paquete" id="id-paquete">
					    <option value="" selected>Seleccione Paquete</option>
						</select>
					  </div>
					  <div class="form-group col-md-4">
					    <label for="exampleInputEmail1" class="form-label">Precio</label>
					    <input type="text" class="form-control" name="precio" id="id-precio" readonly>
					  </div>
					  <div class="form-group col-md-4">
					    <label for="exampleInputEmail1" class="form-label">Cantidad</label>
					    <input type="text" class="form-control" name="cantidad" id="id-cantidad">
					  </div>
					  <div class="form-group col-md-4">
					    <label for="exampleInputEmail1" class="form-label">Total</label>
					    <input type="text" class="form-control" name="total" id="id-total" readonly>
					  </div>
					  <div class="form-group ocultar-caja">
					    <label for="exampleInputEmail1" class="form-label">COD PAQUETE</label>
					    <input type="text" class="form-control" name="codPaquete" id="id-codPaquete" readonly>
					  </div>
					  <div class="modal-footer ">
				        <button type="submit" class="btn btn-dark" style="width:48%">Grabar</button>
				      <button type="button" class="btn btn-secondary" style="width:48%" data-bs-dismiss="modal" id="btn-cerrar">Cerrar</button>
				      </div>
					</form>
			      </div>
			    </div>
			  </div>
			</div>
			<form id="form-eliminar" method="post" action="ServletReservaPaquetes?accion=eliminar">
				<input type="hidden" name="codigo" id="codigoEliminar">
			</form>
			 <form id="form-boleta" method="post" action="ServletReservaPaquetes?accion=reporte">
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
			llenarCombo();
			function llenarTabla(){
				$.get("ServletReservaPaquetes?accion=listar",function(e){
					$.each(e,function(index,item){
						let precioSoles = 'S/ ' + parseFloat(item.prePaquete).toFixed(2);
						let totalSoles = 'S/ ' + parseFloat(item.totalPagar).toFixed(2);
						$("#tableReservaPaq").append(`<tr>
						<td style="vertical-align: middle; text-align:left;">\${item.codReserva}</td>
						<td style="vertical-align: middle;">\${item.nomCliente}</td>
						<td style="vertical-align: middle;">\${item.apeCliente}</td>
						<td style="vertical-align: middle;">\${item.dni}</td>
						<td style="vertical-align: middle;">\${item.telefCliente}</td>
						<td style="vertical-align: middle;">\${item.fechaReserva}</td>
						<td style="vertical-align: middle;">\${item.nomPaquete}</td>
						<td style="vertical-align: middle;">\${precioSoles}</td>
						<td style="vertical-align: middle; text-align:left;">\${item.cantPaquete}</td>
						<td style="vertical-align: middle;">\${totalSoles}</td>
						<td hidden>\${item.codPaquete}</td>
						<td class="d-flex justify-content-between">
						<button type="button" class="btn btn-editar ms-1" style="border-radius:50%;padding: 5px 10px;" data-bs-toggle="modal" data-bs-target="#modalReservaPaq">
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
					new DataTable("#tableReservaPaq",{
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
				$.get("ServletReservaPaquetes?accion=buscarPorCodigo&codigo="+cod,function(data){
					$("#id-codigo").val(data.codReserva);
					$("#id-nombres").val(data.nomCliente);
					$("#id-apellidos").val(data.apeCliente);
					$("#id-dni").val(data.dni);
					$("#id-telefono").val(data.telefCliente);
					$("#id-fecha").val(data.fechaReserva);
					$("#id-paquete").val(data.codPaquete);
					$("#id-precio").val(data.prePaquete);
					$("#id-cantidad").val(data.cantPaquete);
					$("#id-total").val(data.totalPagar);
					$("#id-codPaquete").val(data.codPaquete);
				})
			})
			
				$(document).on("click","#btn-cerrar",function(){
				$("#frmReserva").trigger("reset");
				$("#frmReserva").bootstrapValidator("resetForm",true);
			})
			
		
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
			
			function llenarCombo(){
				$.get("ServletMantPaquetes?accion=listar",function(data){
					$.each(data,function(index,item){
						$("#id-paquete").append(`<option value="\${item.codigoPaquete}">\${item.nombrePaquete}</option>`)
					})
				})
			}
			
			
			//Caputar el precio
			$(document).ready(function() {
		    $('#id-paquete').change(function() {
		        var selectedPaquete = $(this).val();
		
		        $.get('ServletMantPaquetes?accion=obtenerPrecio&codigoPaquete=' + selectedPaquete, function(data) {
		            $('#id-precio').val(data.precioPaquete);
			        });
			    });
			});

			//Captura CODIGO
			$(document).ready(function() {
		    $('#id-paquete').change(function() {
		        var selectedPaquete = $(this).val();
		
		        $.get('ServletMantPaquetes?accion=buscarPorCodigo&codigo=' + selectedPaquete, function(data) {
		            $('#id-precio').val(data.precioPaquete);
		            $('#id-codPaquete').val(data.codigoPaquete);
			        });
			    });
			});
			
			
			//CALCULAR EL TOTAL
			$(document).ready(function() {
		    $('#id-cantidad').change(function() {
		        var cantidad = $(this).val();
		        var precio = $('#id-precio').val();		
		        var total = cantidad * precio;		
		        $('#id-total').val(total);
			    });
			});
		
			
			 $(document).ready(function(){     
			        $('#frmReserva').bootstrapValidator({      
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
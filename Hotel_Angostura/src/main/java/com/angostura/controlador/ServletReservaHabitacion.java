package com.angostura.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import com.angostura.clases.ReservaHabitacion;
import com.angostura.clases.ReservaPaquetes;
import com.angostura.clases.TipoHabitacion;
import com.angostura.dao.MySqlMantPaquetesDAO;
import com.angostura.dao.MySqlReservaHabitacionDAO;
import com.angostura.dao.MySqlReservaPaquetesDAO;
import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ServletReservaHabitacion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletReservaHabitacion() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo=request.getParameter("accion");
		if(tipo.equals("insertar"))
			insertarRegistroHabitacion(request,response);
		else if(tipo.equals("listar"))
			listarRegistroHabitacion(request,response);
		else if(tipo.equals("buscarPorCodigo"))
			buscarRegistroHabitacion(request,response);
		else if(tipo.equals("eliminar"))
			eliminarRegistroHabitacion(request,response);
		else if(tipo.equals("filtro"))
			filtroHabitacion(request,response);
		else if(tipo.equals("reporte"))
			reporteHabitacion(request,response);
	}

	private void reporteHabitacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 response.setContentType("application/pdf");
	        OutputStream out = response.getOutputStream();
	        try {
	            String id = request.getParameter("codigo");
	            if (id != null && !id.isEmpty()) {
	                ReservaHabitacion r = new MySqlReservaHabitacionDAO().reporte(Integer.parseInt(id));
	                
	                Document document = new Document(PageSize.A4.rotate());
	                PdfWriter.getInstance(document, out);
	                document.open();

	             // Título centrado
	             Paragraph title = new Paragraph("HOTEL ANGOSTURA", FontFactory.getFont(FontFactory.HELVETICA, 24, Font.BOLD));
	             title.setAlignment(Element.ALIGN_CENTER);
	             document.add(title);

	             // Línea horizontal
	             Paragraph linea = new Paragraph("--------------------------------------------------"
	             		+ "---------------------------------------------------------"
	             		+ "---------------------------------------------------------------------------");
	             linea.setAlignment(Element.ALIGN_CENTER);
	             document.add(linea);
	             
	             // Título de la boleta
	             Paragraph boleta = new Paragraph("BOLETA RESERVA HABITACIÓN", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD));
	             boleta.setAlignment(Element.ALIGN_CENTER);
	             document.add(boleta);
	             
	             document.add(new Paragraph("\n"));
	             // Información del cliente
	             Paragraph clientInfo = new Paragraph();
	             clientInfo.add(new Phrase("Cliente: " + r.getNombres() + " " + r.getApellidos() + "\n"));
	             clientInfo.add(new Phrase("Teléfono: " + r.getTelefono() + "\n"));
	             clientInfo.add(new Phrase("DNI: " + r.getDni() + "\n"));
	             document.add(clientInfo);

	             // Espacio en blanco
	             document.add(new Paragraph("\n"));

	             // Tabla con detalles de la reserva
	             PdfPTable table = new PdfPTable(5);
	             table.setWidthPercentage(100); // Ancho de la tabla al 100% del ancho de la página

	             // Agregar las celdas con los nombres de las columnas
	             table.addCell("Nro Habitación");
	             table.addCell("Tipo Habitación");
	             table.addCell("Fecha de Ingreso");
	             table.addCell("Fecha de Salida");
	             table.addCell("Estado");
	             
	      
	             
	             // Agregar las celdas con los datos correspondientes
	             table.addCell(r.getNroHabitacion());
	             table.addCell(r.getNombreTipo());
	             table.addCell(String.valueOf(r.getFIngreso()));
	             table.addCell(String.valueOf(r.getFSalida()));
	             table.addCell(r.getEstado());

	             // Agregar la tabla al documento
	             document.add(table);
	             
	             Paragraph linea1 = new Paragraph("--------------------------------------------------"
	              		+ "---------------------------------------------------------"
	              		+ "---------------------------------------------------------------------------");          
	             linea1.setAlignment(Element.ALIGN_CENTER);
	             document.add(linea1);
	             
	             
	             document.add(new Paragraph("\n"));
	          // Mensaje de agradecimiento
	             Paragraph agradecimiento = new Paragraph("¡Gracias por elegir al Hotel Angostura! Esperamos que disfruten");
	             agradecimiento.setAlignment(Element.ALIGN_CENTER);
	             document.add(agradecimiento);

	       
	             document.close();

	            } else {
	                System.out.println("error");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

	private void filtroHabitacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod = request.getParameter("codigo");
		List<ReservaHabitacion> lista = new MySqlReservaHabitacionDAO().findByHabitacion(Integer.parseInt(cod));
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		response.setContentType("application/json");
		PrintWriter salida = response.getWriter();
		salida.println(json);
		
	}

	private void eliminarRegistroHabitacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod;
		cod = request.getParameter("codigo");
		int estado=new MySqlReservaHabitacionDAO().deleteById(Integer.parseInt(cod));
		if(estado==1) // Eliminación OK
			request.getSession().setAttribute("MENSAJE","Paquete eliminado correctamente");
		else 
			request.getSession().setAttribute("MENSAJE","Error al eliminar paquete");
		response.sendRedirect("ReservaHabitacion.jsp");		
	}

	private void buscarRegistroHabitacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cod = request.getParameter("codigo");
        ReservaHabitacion bean = new MySqlReservaHabitacionDAO().findById(Integer.parseInt(cod));
        Gson gson = new Gson();
        String json = gson.toJson(bean);
        response.setContentType("application/json");
        PrintWriter salida = response.getWriter();
        salida.println(json);
	}

	private void listarRegistroHabitacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<ReservaHabitacion> lista = new MySqlReservaHabitacionDAO().findAll();
		for (ReservaHabitacion reserva : lista) {
	        if ("pendiente".equalsIgnoreCase(reserva.getEstado())) {
	            reserva.setEstado("<button class='btn text-danger ms-2' style='padding: 3px 10px;background-color: rgba(220, 53, 69, 0.2);border-radius:30px;font-size:11px;cursor: default; pointer-events: none;'>Pendiente</button>");
	        } else if ("reservado".equalsIgnoreCase(reserva.getEstado())) {
	            reserva.setEstado("<button class='btn text-success ms-2' style='padding: 3px 10px;background-color: rgba(40, 167, 69, 0.2);border-radius:30px;font-size:11px;cursor: default; pointer-events: none;'>Reservado</button>");
	        }
	    }
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		response.setContentType("application/json");
		PrintWriter salida = response.getWriter();
		salida.println(json);
	}

	private void insertarRegistroHabitacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod,nom,ap,dni,tel,finicio,ffin,tiphab,codHabi,est;
		cod = request.getParameter("codigo");
		nom = request.getParameter("nombres");
		ap = request.getParameter("apellidos");
		dni = request.getParameter("dni");
		tel = request.getParameter("telefono");
		finicio = request.getParameter("fechaIngreso");
		ffin = request.getParameter("fechaSalida");
		tiphab = request.getParameter("tipo");
		codHabi = request.getParameter("nro");
		est = request.getParameter("estado");
		ReservaHabitacion bean = new ReservaHabitacion();
		bean.setCodigoReserva(Integer.parseInt(cod));
		bean.setNombres(nom);
		bean.setApellidos(ap);
		bean.setDni(dni);
		bean.setTelefono(tel);
		bean.setFIngreso(finicio);
		bean.setFSalida(ffin);
		bean.setCodigoTipo(Integer.parseInt(tiphab));
		bean.setCodigoHabi(Integer.parseInt(codHabi));
		bean.setEstado(est);
		if(Integer.parseInt(cod)==0) {
			int estado = new MySqlReservaHabitacionDAO().save(bean);
			if(estado == 1)
				request.getSession().setAttribute("MENSAJE", "Registrado correctamente");
			else
				request.getSession().setAttribute("MENSAJE", "Error en el registro");
		} else {
			bean.setCodigoReserva(Integer.parseInt(cod));
			int estado = new MySqlReservaHabitacionDAO().update(bean);
			if(estado==1)
				request.getSession().setAttribute("MENSAJE", "Actualizado correctamente");
			else
				request.getSession().setAttribute("MENSAJE", "Error al actualizar datos");
		}
		response.sendRedirect("ReservaHabitacion.jsp");
	}
}
package com.angostura.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import com.angostura.clases.MantPaquetes;
import com.angostura.clases.ReservaPaquetes;
import com.angostura.dao.MySqlMantPaquetesDAO;
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

public class ServletReservaPaquetes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletReservaPaquetes() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo=request.getParameter("accion");
		if(tipo.equals("insertar"))
			insertarRegistroReservaPaquete(request,response);
		else if(tipo.equals("listar"))
			listarRegistroReservaPaquete(request,response);
		else if(tipo.equals("buscarPorCodigo"))
			buscarRegistroReservaPaquete(request,response);
		
		else if (tipo.equals("obtenerPrecio")) {
		    obtenerPrecio(request, response);
		}
		else if (tipo.equals("obtenerInfoPaquete")) {
		    obtenerInfoPaquete(request, response);
		}
		else if (tipo.equals("eliminar"))
			eliminarReserva(request,response);
		else if(tipo.equals("reporte"))
			reportePaquetes(request,response);
	}


	private void reportePaquetes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 response.setContentType("application/pdf");
	        OutputStream out = response.getOutputStream();
	        try {
	            String id = request.getParameter("codigo");
	            if (id != null && !id.isEmpty()) {
	                ReservaPaquetes r = new MySqlReservaPaquetesDAO().findById(Integer.parseInt(id));
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
	             Paragraph boleta = new Paragraph("BOLETA PAQUETE TURÍSTICO", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLD));
	             boleta.setAlignment(Element.ALIGN_CENTER);
	             document.add(boleta);
	             
	             document.add(new Paragraph("\n"));
	             // Información del cliente
	             Paragraph clientInfo = new Paragraph();
	             clientInfo.add(new Phrase("Cliente: " + r.getNomCliente() + " " + r.getApeCliente() + "\n"));
	             clientInfo.add(new Phrase("Teléfono: " + r.getTelefCliente() + "\n"));
	             clientInfo.add(new Phrase("DNI: " + r.getDni() + "\n"));
	             document.add(clientInfo);

	             // Espacio en blanco
	             document.add(new Paragraph("\n"));

	             // Tabla con detalles de la reserva
	             PdfPTable table = new PdfPTable(5);
	             table.setWidthPercentage(100); // Ancho de la tabla al 100% del ancho de la página

	             // Agregar las celdas con los nombres de las columnas
	             table.addCell("Código de Reserva");
	             table.addCell("Paquete");
	             table.addCell("Fecha de reserva");
	             table.addCell("Cant. Personas");
	             table.addCell("Precio Total");

	             // Agregar las celdas con los datos correspondientes
	             table.addCell(String.valueOf(r.getCodReserva()));
	             table.addCell(r.getNomPaquete());
	             table.addCell(r.getFechaReserva());
	             table.addCell(String.valueOf(r.getCantPaquete()));
	             table.addCell(String.valueOf(r.getTotalPagar()));

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

	private void eliminarReserva(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod;
		cod = request.getParameter("codigo");
		int estado=new MySqlReservaPaquetesDAO().deleteById(Integer.parseInt(cod));
		if(estado==1)
			request.getSession().setAttribute("MENSAJE","Reserva eliminado correctamente");
		else 
			request.getSession().setAttribute("MENSAJE","Error al eliminar Reserva");
		response.sendRedirect("ReservaPaquetes.jsp");
	}

	private void obtenerInfoPaquete(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    String codigoPaquete = request.getParameter("codigoPaquete");
	    MantPaquetes paquete = new MySqlMantPaquetesDAO().findById(Integer.parseInt(codigoPaquete));
	    
	    response.setContentType("application/json");
	    Gson gson = new Gson();
	    String json = gson.toJson(paquete);
	    PrintWriter salida = response.getWriter();
	    salida.println(json);
	}

	private void obtenerPrecio(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    String codigoPaquete = request.getParameter("codigoPaquete");
	    MantPaquetes paquete = new MySqlMantPaquetesDAO().findById(Integer.parseInt(codigoPaquete));
	    
	    response.setContentType("application/json");
	    Gson gson = new Gson();
	    String json = gson.toJson(paquete);
	    PrintWriter salida = response.getWriter();
	    salida.println(json);
	}

	private void buscarRegistroReservaPaquete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("codigo");
		ReservaPaquetes bean = new MySqlReservaPaquetesDAO().findById(Integer.parseInt(id));
        Gson gson = new Gson();
        String json = gson.toJson(bean);
        response.setContentType("application/json");
        PrintWriter salida = response.getWriter();
        salida.println(json);		
	}

	private void listarRegistroReservaPaquete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<ReservaPaquetes> lista = new MySqlReservaPaquetesDAO().findAll();
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		response.setContentType("application/json");
		PrintWriter salida = response.getWriter();
		salida.println(json);	
	}

	private void insertarRegistroReservaPaquete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod, nom, ape, dni, tel, fec, paq, can, pre, tot, codpaq;
		cod = request.getParameter("codigo");
		nom = request.getParameter("nombres");
		ape = request.getParameter("apellidos");
		dni = request.getParameter("dni");
		tel = request.getParameter("telefono");
		fec = request.getParameter("fecha");
		paq = request.getParameter("paquete");
		pre = request.getParameter("precio");
		can = request.getParameter("cantidad");
		tot = request.getParameter("total");
		codpaq = request.getParameter("codPaquete");
		ReservaPaquetes bean = new ReservaPaquetes();
		bean.setCodReserva(Integer.parseInt(cod));
		bean.setNomCliente(nom);
		bean.setApeCliente(ape);
		bean.setDni(dni);
		bean.setTelefCliente(tel);
		bean.setFechaReserva(fec);
		bean.setNomPaquete(paq);
		bean.setPrePaquete(Double.parseDouble(pre));
		bean.setCantPaquete(Integer.parseInt(can));
		bean.setTotalPagar(Double.parseDouble(tot));
		bean.setCodPaquete(Integer.parseInt(codpaq));
		if(Integer.parseInt(cod)==0) {
			int estado = new MySqlReservaPaquetesDAO().save(bean);
		if(estado == 1)
				request.getSession().setAttribute("MENSAJE", "Registro correctamente");
		else
				request.getSession().setAttribute("MENSAJE", "Error en el registro");
		} else {
			bean.setCodReserva(Integer.parseInt(cod));
			int estado = new MySqlReservaPaquetesDAO().update(bean);
			if(estado == 1)
				request.getSession().setAttribute("MENSAJE", "Actualizado correctamente");
			else
				request.getSession().setAttribute("MENSAJE", "Error al actualizar datos");
		}
		response.sendRedirect("ReservaPaquetes.jsp");	
	}
}
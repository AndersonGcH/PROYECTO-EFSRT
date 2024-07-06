package com.angostura.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.angostura.clases.MantHabitaciones;
import com.angostura.clases.MantPaquetes;
import com.angostura.dao.MySqlMantHabitacionesDAO;
import com.angostura.dao.MySqlMantPaquetesDAO;
import com.google.gson.Gson;

public class ServletMantHabitacion extends HttpServlet {
	private static final long serialVersionUID = 1L;      

    public ServletMantHabitacion() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo=request.getParameter("accion");
		if(tipo.equals("insertar"))
			insertarHabitaciones(request,response);
		else if(tipo.equals("eliminar"))
			eliminarHabitaciones(request,response);
		else if(tipo.equals("listar"))
			listarHabitaciones(request,response);
		else if(tipo.equals("buscarPorCodigo"))
			buscarHabitacionesPorCodigo(request,response);
	}

	private void buscarHabitacionesPorCodigo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod=request.getParameter("codigo");
		MantHabitaciones mh=new MySqlMantHabitacionesDAO().findByID(Integer.parseInt(cod));
		Gson gson=new Gson();
		String json=gson.toJson(mh);
		response.setContentType("application/json");
		PrintWriter salida=response.getWriter();
		salida.println(json);	
	}

	private void listarHabitaciones(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<MantHabitaciones>lista=new MySqlMantHabitacionesDAO().findAll();
		Gson gson=new Gson();
		String json=gson.toJson(lista);
		response.setContentType("application/json");
		PrintWriter salida=response.getWriter();
		salida.println(json);
	}

	private void eliminarHabitaciones(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod;
		cod = request.getParameter("codigo");
		int estado=new MySqlMantHabitacionesDAO().deleteByID(Integer.parseInt(cod));
		if(estado==1) 
			request.getSession().setAttribute("MENSAJE","Habitación eliminada correctamente");
		else 
			request.getSession().setAttribute("MENSAJE","Error al eliminar habitación");
		response.sendRedirect("MantHabitacion.jsp");
	}

	private void insertarHabitaciones(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod, num, descri, tipo, precio;
		cod = request.getParameter("codigo");
		num = request.getParameter("numero");
		tipo = request.getParameter("tipo");
		descri = request.getParameter("descripcion");
		precio = request.getParameter("precio");
		MantHabitaciones mh = new MantHabitaciones();
		mh.setNumeroHabi(num);
		mh.setCodigoTipo(Integer.parseInt(tipo));
		mh.setDescripcion(descri);
		mh.setPrecio(Double.parseDouble(precio));
		if(Integer.parseInt(cod)==0) {
			int estado = new MySqlMantHabitacionesDAO().save(mh);
			if(estado==1)
				request.getSession().setAttribute("MENSAJE","Paquete registrado correctamente");
			else 
				request.getSession().setAttribute("MENSAJE","Error en el registro");
		}
		else {
			mh.setCodigoHabi(Integer.parseInt(cod));
			int estado=new MySqlMantHabitacionesDAO().update(mh);
			if(estado==1) 
				request.getSession().setAttribute("MENSAJE","Paquete actualizado correctamente");
			else 
				request.getSession().setAttribute("MENSAJE","Error en la actualización");
		}
		response.sendRedirect("MantHabitacion.jsp");	
	}
}
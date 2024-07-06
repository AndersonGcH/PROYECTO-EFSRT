package com.angostura.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.angostura.clases.MantPaquetes;
import com.angostura.dao.MySqlMantPaquetesDAO;
import com.google.gson.Gson;

public class ServletMantPaquetes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletMantPaquetes() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo=request.getParameter("accion");
		if(tipo.equals("insertar"))
			insertarPaquetes(request,response);
		else if(tipo.equals("eliminar"))
			eliminarPaquetes(request,response);
		else if(tipo.equals("listar"))
			listarPaquetes(request,response);
		else if(tipo.equals("buscarPorCodigo"))
			buscarPaquetesPorCodigo(request,response);
	}

	private void buscarPaquetesPorCodigo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod=request.getParameter("codigo");
		MantPaquetes paq=new MySqlMantPaquetesDAO().findById(Integer.parseInt(cod));
		Gson gson=new Gson();
		String json=gson.toJson(paq);
		response.setContentType("application/json");
		PrintWriter salida=response.getWriter();
		salida.println(json);
	}

	private void listarPaquetes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<MantPaquetes> lista = new MySqlMantPaquetesDAO().findAll();
		Gson gson=new Gson();
		String json=gson.toJson(lista);
		response.setContentType("application/json");
		PrintWriter salida=response.getWriter();
		salida.println(json);
	}

	private void eliminarPaquetes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod;
		cod = request.getParameter("codigo");
		int estado=new MySqlMantPaquetesDAO().deleteById(Integer.parseInt(cod));
		if(estado==1)
			request.getSession().setAttribute("MENSAJE","Paquete eliminado correctamente");
		else 
			request.getSession().setAttribute("MENSAJE","Error al eliminar paquete");
		response.sendRedirect("MantPaquetes.jsp");
	}

	private void insertarPaquetes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cod, nompaq, descri, precio;
		cod = request.getParameter("codigo");
		nompaq = request.getParameter("nombre");
		descri = request.getParameter("descripcion");
		precio = request.getParameter("precio");
		MantPaquetes mp = new MantPaquetes();
		mp.setNombrePaquete(nompaq);
		mp.setDescripPaquete(descri);
		mp.setPrecioPaquete(Double.parseDouble(precio));
		if(Integer.parseInt(cod)==0) {
			int estado = new MySqlMantPaquetesDAO().save(mp);
			if(estado==1)
				request.getSession().setAttribute("MENSAJE","Paquete registrado correctamente");
			else 
				request.getSession().setAttribute("MENSAJE","Error en el registro");
		}
		else {
			mp.setCodigoPaquete(Integer.parseInt(cod));
			int estado=new MySqlMantPaquetesDAO().update(mp);
			if(estado==1) 
				request.getSession().setAttribute("MENSAJE","Paquete actualizado correctamente");
			else 
				request.getSession().setAttribute("MENSAJE","Error en la actualización");
		}
		response.sendRedirect("MantPaquetes.jsp");	
	}
}
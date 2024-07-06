package com.angostura.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.angostura.clases.TipoHabitacion;
import com.angostura.dao.MySqlTipoHabitacionesDAO;
import com.google.gson.Gson;

public class ServletTipoHabitaciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ServletTipoHabitaciones() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TipoHabitacion> lista = new MySqlTipoHabitacionesDAO().findAll();
		Gson gson = new Gson();
		String json = gson.toJson(lista);
		response.setContentType("application/json");
		PrintWriter salida = response.getWriter();
		salida.println(json);
	}
}
package com.angostura.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.angostura.clases.Enlace;
import com.angostura.clases.Trabajadores;
import com.angostura.dao.MySqlUsuarioDAO;

public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L; 

    public ServletUsuario() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("accion");
		if(tipo.equals("INICIARSESION"))
			loginDelUsuario(request, response);
		else if(tipo.equals("CERRARSESION"))
			cerrarSesionDelUsuario(request, response);
	}

	private void cerrarSesionDelUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getSession().setAttribute("TERMINADA", "Sesión terminada");
		response.sendRedirect("Login.jsp");
	}

	private void loginDelUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String vLogin, vClave;
		vLogin = request.getParameter("username");
		vClave = request.getParameter("password");
		
		Trabajadores bean = new MySqlUsuarioDAO().iniciarSesion(vLogin, vClave);
		if(bean == null) {
			request.getSession().setAttribute("CERRAR", "Credenciales inválidas");
			response.sendRedirect("Login.jsp");
		}
		else {
			request.getSession().setAttribute("DATOS", bean.getPaterno()+" "+ bean.getMaterno()+", "+bean.getNombre());
			
			String fotoUsuario = "";
	        String iconoUsuario = "";
	        if (bean.getCodigoRol() == 1) {
	            fotoUsuario = "img/foto-administrador.jpg";
	            iconoUsuario = "<i class='bx bxs-cog' style=\"width:40px; font-size:18px;\"></i>";
	        } else if (bean.getCodigoRol() == 2) {
	            fotoUsuario = "img/foto-recepcionista.jpg";
	            iconoUsuario = "<i class='bx bx-calendar' style=\"width:40px; font-size:18px;\"></i>";
	        }
	        request.getSession().setAttribute("FOTO", fotoUsuario);
	        request.getSession().setAttribute("ICONO", iconoUsuario);
	        
			List<Enlace> lista = new MySqlUsuarioDAO().traerMenusDelUsuario(bean.getCodigoRol());
			request.getSession().setAttribute("MENUS", lista);
			response.sendRedirect("MenuPrincipal.jsp");	
		}
	}
}
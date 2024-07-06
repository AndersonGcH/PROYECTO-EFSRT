package com.angostura.interfaces;

import java.util.List;

import com.angostura.clases.Enlace;
import com.angostura.clases.Trabajadores;

public interface UsuarioDAO {
	Trabajadores iniciarSesion(String login, String clave);
	List<Enlace> traerMenusDelUsuario(int codRol);
}

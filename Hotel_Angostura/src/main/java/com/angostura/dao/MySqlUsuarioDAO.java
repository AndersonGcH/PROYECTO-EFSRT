package com.angostura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.angostura.clases.Enlace;
import com.angostura.clases.Trabajadores;
import com.angostura.interfaces.UsuarioDAO;
import com.angostura.utils.MySqlConexion;

public class MySqlUsuarioDAO implements UsuarioDAO{

	@Override
	public Trabajadores iniciarSesion(String login, String clave) {
		Trabajadores tra = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select t.cod_trab, t.nom_trab, t.pat_trab,t.mat_trab, u.idrol\r\n"
					+ "From tb_usuario u join tb_trabajadores t on t.cod_trab=u.cod_trab\r\n"
					+ "where u.login=? and u.pass=?";
			pstm=cn.prepareStatement(sql);
			// Paso 4: Parámetros
			pstm.setString(1, login);
			pstm.setString(2, clave);
			rs = pstm.executeQuery();
			if(rs.next()) {
				tra = new Trabajadores();
				tra.setCodigo(rs.getInt(1));
				tra.setNombre(rs.getString(2));
				tra.setPaterno(rs.getString(3));
				tra.setMaterno(rs.getString(4));
				tra.setCodigoRol(rs.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return tra;
	}

	@Override
	public List<Enlace> traerMenusDelUsuario(int codRol) {
		List<Enlace> lista = new ArrayList<Enlace>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="Select e.idenlace, e.descripcion, e.ruta\r\n"
					+ "from tb_rol_enlace re join tb_enlace e on re.idenlace=e.idenlace\r\n"
					+ "where re.idrol=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, codRol);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Enlace e = new Enlace();
				e.setCodigo(rs.getInt(1));
				e.setDescripcion(rs.getString(2));
				e.setRuta(rs.getString(3));
				lista.add(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}

}

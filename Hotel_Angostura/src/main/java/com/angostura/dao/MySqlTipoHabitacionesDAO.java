package com.angostura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.angostura.clases.TipoHabitacion;
import com.angostura.interfaces.TipoHabitacionDAO;
import com.angostura.utils.MySqlConexion;

public class MySqlTipoHabitacionesDAO implements TipoHabitacionDAO {
	@Override
	public List<TipoHabitacion> findAll() {
		List<TipoHabitacion> lista = new ArrayList<TipoHabitacion>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select * from tb_tipohabitaciones";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()) {
				TipoHabitacion th = new TipoHabitacion();
				th.setCodigoTipo(rs.getInt(1));
				th.setNombreTipo(rs.getString(2));
				lista.add(th);
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

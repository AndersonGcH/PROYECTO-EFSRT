package com.angostura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.angostura.clases.MantHabitaciones;
import com.angostura.interfaces.MantHabitacionesDAO;
import com.angostura.utils.MySqlConexion;

public class MySqlMantHabitacionesDAO implements MantHabitacionesDAO{

	@Override
	public int save(MantHabitaciones bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySqlConexion.getConexion();
			String sql = "insert into tb_mantHabitacion values(null,?,?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getNumeroHabi());
			pstm.setInt(2, bean.getCodigoTipo());
			pstm.setString(3, bean.getDescripcion());
			pstm.setDouble(4, bean.getPrecio());
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public int update(MantHabitaciones bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="update tb_manthabitacion set nro_hab=?,cod_tip_hab=?,des_hab=?,pre_hab=? where cod_hab=?"; 
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getNumeroHabi());
			pstm.setInt(2, bean.getCodigoTipo());
			pstm.setString(3, bean.getDescripcion());
			pstm.setDouble(4, bean.getPrecio());
			pstm.setInt(5, bean.getCodigoHabi());
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			try {
				if(pstm!= null) pstm.close();
				if(cn!= null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public int deleteByID(int cod) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="delete from tb_mantHabitacion where cod_hab=?";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstm!= null) pstm.close();
				if(cn!= null) cn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public List<MantHabitaciones> findAll() {
		List<MantHabitaciones>lista=new ArrayList<MantHabitaciones>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select * from tb_manthabitacion m \r\n"
					+ "inner join tb_tipohabitaciones t on m.cod_tip_hab=t.cod_tip_hab;";
			pstm=cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				MantHabitaciones mh = new MantHabitaciones();
				mh.setCodigoHabi(rs.getInt(1));
				mh.setNumeroHabi(rs.getString(2));
				mh.setCodigoTipo(rs.getInt(3));
				mh.setDescripcion(rs.getString(4));
				mh.setPrecio(rs.getDouble(5));
				mh.setNombreTipo(rs.getString(7));
				lista.add(mh);
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

	@Override
	public MantHabitaciones findByID(int cod) {
		MantHabitaciones mh = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select *from tb_mantHabitacion where cod_hab=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			rs = pstm.executeQuery();
			if(rs.next()) {
				mh = new MantHabitaciones();
				mh.setCodigoHabi(rs.getInt(1));
				mh.setNumeroHabi(rs.getString(2));
				mh.setCodigoTipo(rs.getInt(3));
				mh.setDescripcion(rs.getString(4));
				mh.setPrecio(rs.getDouble(5));
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
		return mh;
	}

	@Override
	public List<MantHabitaciones> findAllByTipo(int codTipo) {
		List<MantHabitaciones> lista = new ArrayList<MantHabitaciones>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select *from tb_empleado where cod_tip_emp=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, codTipo);
			rs = pstm.executeQuery();
			while(rs.next()) {
				MantHabitaciones e = new MantHabitaciones();
				e.setCodigoHabi(rs.getInt(1));
				e.setNumeroHabi(rs.getString(2));
				e.setCodigoTipo(rs.getInt(3));
				e.setDescripcion(rs.getString(4));
				e.setPrecio(rs.getDouble(5));
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

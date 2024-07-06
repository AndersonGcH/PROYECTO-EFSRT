package com.angostura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.angostura.clases.MantPaquetes;
import com.angostura.interfaces.MantPaquetesDAO;
import com.angostura.utils.MySqlConexion;

public class MySqlMantPaquetesDAO implements MantPaquetesDAO{

	@Override
	public int save(MantPaquetes bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql = "insert into tb_mantPaquete values(null,?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getNombrePaquete());
			pstm.setString(2, bean.getDescripPaquete());
			pstm.setDouble(3, bean.getPrecioPaquete());
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
	public int update(MantPaquetes bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql = "update tb_mantPaquete set nom_paquete=?,descri_paquete=?,precio_paq=? where cod_paquete=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getNombrePaquete());
			pstm.setString(2, bean.getDescripPaquete());
			pstm.setDouble(3, bean.getPrecioPaquete());
			pstm.setInt(4, bean.getCodigoPaquete());
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
	public int deleteById(int cod) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="delete from tb_mantPaquete where cod_paquete=?";
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
	public List<MantPaquetes> findAll() {
		List<MantPaquetes> lista = new ArrayList<MantPaquetes>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select *from tb_mantPaquete";
			pstm=cn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				MantPaquetes e = new MantPaquetes();
				e.setCodigoPaquete(rs.getInt(1));
				e.setNombrePaquete(rs.getString(2));
				e.setDescripPaquete(rs.getString(3));
				e.setPrecioPaquete(rs.getDouble(4));
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

	@Override
	public MantPaquetes findById(int cod) {
		MantPaquetes obj=null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select *from tb_mantPaquete where cod_paquete=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, cod);
			rs = pstm.executeQuery();
			if(rs.next()) {
				obj = new MantPaquetes();
				obj.setCodigoPaquete(rs.getInt(1));
				obj.setNombrePaquete(rs.getString(2));
				obj.setDescripPaquete(rs.getString(3));
				obj.setPrecioPaquete(rs.getDouble(4));
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
		return obj;
	}

}
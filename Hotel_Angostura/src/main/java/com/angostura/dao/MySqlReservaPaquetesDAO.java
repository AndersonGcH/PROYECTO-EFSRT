package com.angostura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.angostura.clases.ReservaPaquetes;
import com.angostura.interfaces.ReservaPaquetesDAO;
import com.angostura.utils.MySqlConexion;

public class MySqlReservaPaquetesDAO implements ReservaPaquetesDAO{

	@Override
	public int save(ReservaPaquetes bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySqlConexion.getConexion();
			String sql = "insert into tb_reservaPaquete values(null,?,?,?,?,?,?,?,?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getNomCliente());
			pstm.setString(2, bean.getApeCliente());
			pstm.setString(3, bean.getDni());
			pstm.setString(4, bean.getTelefCliente());
			pstm.setString(5, bean.getFechaReserva());
			pstm.setString(6, bean.getNomPaquete());
			pstm.setDouble(7, bean.getPrePaquete());
			pstm.setInt(8, bean.getCantPaquete());
			pstm.setDouble(9, bean.getTotalPagar());
			pstm.setInt(10, bean.getCodPaquete());
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
	public List<ReservaPaquetes> findAll() {
		List<ReservaPaquetes> lista = new ArrayList<ReservaPaquetes>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = MySqlConexion.getConexion();
			String sql = "select * from tb_reservapaquete r inner join tb_mantpaquete m on r.cod_paquete = m.cod_paquete;";
			pstm = cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()) {
				ReservaPaquetes rp = new ReservaPaquetes();
				rp.setCodReserva(rs.getInt(1));
				rp.setNomCliente(rs.getString(2));
				rp.setApeCliente(rs.getString(3));
				rp.setDni(rs.getString(4));
				rp.setTelefCliente(rs.getString(5));
				rp.setFechaReserva(rs.getString(6));
				rp.setPrePaquete(rs.getDouble(8));
				rp.setCantPaquete(rs.getInt(9));
				rp.setTotalPagar(rs.getDouble(10));
				rp.setCodPaquete(rs.getInt(11));
				rp.setNomPaquete(rs.getString(13));
				lista.add(rp);
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
	public ReservaPaquetes findById(int cod) {
		ReservaPaquetes obj=null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select * from tb_reservaPaquete where cod_res_paq=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1,cod);
			rs=pstm.executeQuery();
			if(rs.next()) {
				obj = new ReservaPaquetes();
				obj.setCodReserva(rs.getInt(1));
				obj.setNomCliente(rs.getString(2));
				obj.setApeCliente(rs.getString(3));
				obj.setDni(rs.getString(4));
				obj.setTelefCliente(rs.getString(5));
				obj.setFechaReserva(rs.getString(6));
				obj.setNomPaquete(rs.getString(7));
				obj.setPrePaquete(rs.getDouble(8));
				obj.setCantPaquete(rs.getInt(9));
				obj.setTotalPagar(rs.getDouble(10));
				obj.setCodPaquete(rs.getInt(11));
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

	@Override
	public int update(ReservaPaquetes bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="update tb_reservaPaquete set nombres_cli=?,apellidos_cli=?,dni_cli=?,telefono_cli=?,fecha_reserva=?,nom_paquete=?,cant_paq=?,precio_paq=?,tot_paq=?,cod_paquete=? where cod_res_paq=?";		
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getNomCliente());
			pstm.setString(2, bean.getApeCliente());
			pstm.setString(3, bean.getDni());
			pstm.setString(4, bean.getTelefCliente());
			pstm.setString(5, bean.getFechaReserva());
			pstm.setString(6, bean.getNomPaquete());
			pstm.setInt(7, bean.getCantPaquete());	
			pstm.setDouble(8, bean.getPrePaquete());
			pstm.setDouble(9, bean.getTotalPagar());
			pstm.setInt(10, bean.getCodPaquete());
			pstm.setInt(11, bean.getCodReserva());
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
	public List<ReservaPaquetes> findAllByPaquetes(int codPaq) {
		List<ReservaPaquetes> lista = new ArrayList<ReservaPaquetes>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select *from tb_reservaPaquete where cod_res_paq=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, codPaq);
			rs = pstm.executeQuery();
			while(rs.next()) {
				ReservaPaquetes rp = new ReservaPaquetes();
				rp.setCodReserva(rs.getInt(1));
				rp.setNomCliente(rs.getString(2));
				rp.setApeCliente(rs.getString(3));
				rp.setDni(rs.getString(4));
				rp.setTelefCliente(rs.getString(5));
				rp.setFechaReserva(rs.getString(6));
				rp.setNomPaquete(rs.getString(7));
				rp.setPrePaquete(rs.getDouble(8));
				rp.setCantPaquete(rs.getInt(9));
				rp.setTotalPagar(rs.getDouble(10));
				rp.setCodPaquete(rs.getInt(11));
				lista.add(rp);
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
	public int deleteById(int cod) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="delete from tb_reservaPaquete where cod_res_paq=?";
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

}

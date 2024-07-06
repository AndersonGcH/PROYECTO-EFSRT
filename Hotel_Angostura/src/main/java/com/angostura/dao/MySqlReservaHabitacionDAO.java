package com.angostura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.angostura.clases.ReservaHabitacion;
import com.angostura.interfaces.ReservaHabitacionDAO;
import com.angostura.utils.MySqlConexion;

public class MySqlReservaHabitacionDAO implements ReservaHabitacionDAO{

	@Override
	public List<ReservaHabitacion> findAll() {
		List<ReservaHabitacion> lista = new ArrayList<ReservaHabitacion>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select * from tb_reservahabitaciones r \r\n"
					+ "inner join tb_tipohabitaciones t on r.cod_tip_hab=t.cod_tip_hab\r\n"
					+ "INNER JOIN tb_mantHabitacion m ON r.cod_hab = m.cod_hab";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()) {
				ReservaHabitacion r = new ReservaHabitacion();
				r.setCodigoReserva(rs.getInt(1));
				r.setNombres(rs.getString(2));
				r.setApellidos(rs.getString(3));
				r.setDni(rs.getString(4));
				r.setTelefono(rs.getString(5));
				r.setFIngreso(rs.getString(6));
				r.setFSalida(rs.getString(7));
				r.setCodigoHabi(rs.getInt(8));
				r.setCodigoTipo(rs.getInt(9));
				r.setNombreTipo(rs.getString(12));
				r.setNroHabitacion(rs.getString(14));
				r.setEstado(rs.getString(10));
				lista.add(r);
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
	public ReservaHabitacion findById(int cod) {
		ReservaHabitacion obj=null;
			Connection cn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			try {
				cn=MySqlConexion.getConexion();
				String sql="select * from tb_reservaHabitaciones where cod_res_hab=?";
				pstm=cn.prepareStatement(sql);
				pstm.setInt(1,cod);
				rs=pstm.executeQuery();
				if(rs.next()) {
					obj = new ReservaHabitacion();
					obj.setCodigoReserva(rs.getInt(1));
					obj.setNombres(rs.getString(2));
					obj.setApellidos(rs.getString(3));
					obj.setDni(rs.getString(4));
					obj.setTelefono(rs.getString(5));
					obj.setFIngreso(rs.getString(6));
					obj.setFSalida(rs.getString(7));
					obj.setCodigoHabi(rs.getInt(8));
					obj.setCodigoTipo(rs.getInt(9));
					obj.setEstado(rs.getString(10));
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
	public int deleteById(int cod) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="delete from tb_reservaHabitaciones where cod_res_hab=?";
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
	public int save(ReservaHabitacion bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn = MySqlConexion.getConexion();
			String sql = "insert into tb_reservaHabitaciones values(null,?,?,?,?,?,?,?,?,?)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getNombres());
			pstm.setString(2, bean.getApellidos());
			pstm.setString(3, bean.getDni());
			pstm.setString(4, bean.getTelefono());
			pstm.setString(5, bean.getFIngreso());
			pstm.setString(6, bean.getFSalida());
			pstm.setInt(7, bean.getCodigoHabi());
			pstm.setInt(8, bean.getCodigoTipo());
			pstm.setString(9, bean.getEstado());
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
	public int update(ReservaHabitacion bean) {
		int salida = -1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="update tb_reservaHabitaciones set nom_cli=?,ape_cli=?,dni=?,tel_cli=?,fecha_ingreso=?,fecha_salida=?,cod_hab=?,cod_tip_hab=?,estado=? where cod_res_hab=?";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, bean.getNombres());
			pstm.setString(2, bean.getApellidos());
			pstm.setString(3, bean.getDni());
			pstm.setString(4, bean.getTelefono());
			pstm.setString(5, bean.getFIngreso());
			pstm.setString(6, bean.getFSalida());
			pstm.setInt(7, bean.getCodigoHabi());
			pstm.setInt(8, bean.getCodigoTipo());
			pstm.setString(9, bean.getEstado());
			pstm.setInt(10, bean.getCodigoReserva());
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
	public List<ReservaHabitacion> findByHabitacion(int codHab) {
		List<ReservaHabitacion> lista = new ArrayList<ReservaHabitacion>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select *\r\n"
					+ "from \r\n"
					+ "tb_tipohabitaciones t join tb_mantHabitacion m on t.cod_tip_hab = m.cod_tip_hab\r\n"
					+ "where t.cod_tip_hab=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1,codHab);
			rs=pstm.executeQuery();
			while(rs.next()) {
				ReservaHabitacion r= new ReservaHabitacion();
				r.setCodigoTipo(rs.getInt(1));
				r.setCodigoHabi(rs.getInt(3));
				r.setNroHabitacion(rs.getString(4));
				lista.add(r);
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
	public ReservaHabitacion reporte(int codHab) {
		ReservaHabitacion obj=null;
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn=MySqlConexion.getConexion();
			String sql="select *\r\n"
					+ "from \r\n"
					+ "tb_tipohabitaciones t join tb_mantHabitacion m on t.cod_tip_hab = m.cod_tip_hab\r\n"
					+ "join tb_reservahabitaciones r on m.cod_hab = r.cod_hab\r\n"
					+ "where r.cod_res_hab=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1,codHab);
			rs=pstm.executeQuery();
			if(rs.next()) {
				obj = new ReservaHabitacion();
				obj.setNombreTipo(rs.getString(2));
				obj.setNroHabitacion(rs.getString(4));
				obj.setNombres(rs.getString(9));
				obj.setApellidos(rs.getString(10));
				obj.setDni(rs.getString(11));
				obj.setTelefono(rs.getString(12));
				obj.setFIngreso(rs.getString(13));
				obj.setFSalida(rs.getString(14));
				obj.setEstado(rs.getString(17));
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

package com.angostura.interfaces;

import java.util.List;

import com.angostura.clases.ReservaHabitacion;


public interface ReservaHabitacionDAO {
	int save(ReservaHabitacion bean);
	List<ReservaHabitacion> findAll();
	ReservaHabitacion findById(int cod);
	int update(ReservaHabitacion bean);
	int deleteById(int cod);
	List<ReservaHabitacion> findByHabitacion(int codHab);
	ReservaHabitacion reporte(int codHab);
}

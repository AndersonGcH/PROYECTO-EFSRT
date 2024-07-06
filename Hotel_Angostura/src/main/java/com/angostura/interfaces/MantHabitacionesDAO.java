package com.angostura.interfaces;

import java.util.List;

import com.angostura.clases.MantHabitaciones;


public interface MantHabitacionesDAO {
	int save(MantHabitaciones bean);
	int update(MantHabitaciones bean);
	int deleteByID(int cod);
	List<MantHabitaciones>findAll();
	List<MantHabitaciones> findAllByTipo(int codTipo);
	MantHabitaciones findByID(int cod);	
}

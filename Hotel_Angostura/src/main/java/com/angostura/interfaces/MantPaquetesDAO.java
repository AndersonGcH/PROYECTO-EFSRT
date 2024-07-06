package com.angostura.interfaces;

import java.util.List;

import com.angostura.clases.MantPaquetes;

public interface MantPaquetesDAO {
	int save(MantPaquetes bean);
	int update(MantPaquetes bean);
	int deleteById(int cod);
	List<MantPaquetes> findAll();
	MantPaquetes findById(int cod);
}

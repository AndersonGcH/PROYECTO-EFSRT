package com.angostura.interfaces;

import java.util.List;

import com.angostura.clases.ReservaPaquetes;


public interface ReservaPaquetesDAO {
	List<ReservaPaquetes> findAllByPaquetes(int codPaq);
	int save(ReservaPaquetes bean);
	List<ReservaPaquetes> findAll();
	ReservaPaquetes findById(int cod);
	int update(ReservaPaquetes bean);
	int deleteById(int cod);
}

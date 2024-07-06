package com.angostura.clases;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MantHabitaciones {
	private int codigoHabi, codigoTipo;
	private String numeroHabi, descripcion, nombreTipo;
	private double precio;
}

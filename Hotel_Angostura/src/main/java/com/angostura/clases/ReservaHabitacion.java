package com.angostura.clases;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaHabitacion {
	private int codigoReserva, codigoHabi, codigoTipo;
	private String  nombres, apellidos, dni, telefono, fIngreso, fSalida, nombreTipo, nroHabitacion, estado;
	
}

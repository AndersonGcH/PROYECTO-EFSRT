package com.angostura.clases;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservaPaquetes {
	private int codReserva, codPaquete, cantPaquete;
	private String nomCliente, apeCliente, dni, telefCliente, fechaReserva, nomPaquete;
	private double prePaquete, totalPagar;
}

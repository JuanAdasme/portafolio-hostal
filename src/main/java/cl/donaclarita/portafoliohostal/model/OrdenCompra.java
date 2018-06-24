package cl.donaclarita.portafoliohostal.model;

import java.util.Date;

import lombok.Data;

@Data
public class OrdenCompra {
	private int id;
	private Date fechaInicio;
	private Date fechaTermino;
	private int empresaRut;
}

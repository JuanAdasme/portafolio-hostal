package cl.donaclarita.portafoliohostal.model;

import java.util.Date;

import lombok.Data;

@Data
public class Factura {
	private int id;
	private int valorTotal;
	private Date fecha;
	private int empresaRut;
}
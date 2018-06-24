package cl.donaclarita.portafoliohostal.model;

import java.util.Date;

import lombok.Data;

@Data
public class RecepcionProducto {
	private int id;
	private int precioTotal;
	private Date fecha;
	private int empleadoRut;
	private int proveedorId;
}

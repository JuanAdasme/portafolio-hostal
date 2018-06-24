package cl.donaclarita.portafoliohostal.model;

import java.util.Date;

import lombok.Data;

@Data
public class OrdenPedido {
	private int id;
	private int precioTotal;
	private Date fecha;
	private int empleadoId;
	private int proveedorId;
}

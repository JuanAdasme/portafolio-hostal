package cl.donaclarita.portafoliohostal.model;

import lombok.Data;

@Data
public class DetalleRecepcion {
	private int id;
	private int cantidad;
	private int precio;
	private int recepcionId;
	private int productoId;
}

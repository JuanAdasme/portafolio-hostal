package cl.donaclarita.portafoliohostal.model;

import lombok.Data;

@Data
public class DetalleCompra {
	private int id;
	private int huespedeRut;
	private int habitacionId;
	private int ordenCompraId;
}


package cl.donaclarita.portafoliohostal.model;

import lombok.Data;

@Data
public class DetalleFactura {
	private int id;
	private int precio;
	private int estadia;
	private int facturaId;
	private int menuId;
	private int habitacionNumero;
}

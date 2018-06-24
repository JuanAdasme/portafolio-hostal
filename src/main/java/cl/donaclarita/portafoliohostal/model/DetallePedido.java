package cl.donaclarita.portafoliohostal.model;

import lombok.Data;

@Data
public class DetallePedido {
	private int id;
	private int cantidad;
	private int precio;
	private int ordenPedidoId;
	private int proveedorId;
}

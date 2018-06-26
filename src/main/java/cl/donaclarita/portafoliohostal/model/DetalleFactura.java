package cl.donaclarita.portafoliohostal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DetalleFactura {
	private int detallE_FACTURA_ID;
	private int detallE_FACTURA_PRECIO;
	private int detallE_FACTURA_ESTADIA;
	private int facturA_ID;
	private int tipO_MENU_ID;
	private int habitacioN_NRO;
}

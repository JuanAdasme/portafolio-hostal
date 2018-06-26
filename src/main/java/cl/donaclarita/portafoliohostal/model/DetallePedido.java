package cl.donaclarita.portafoliohostal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedido {
	private Long detallE_PEDIDO_ID;
	private Long detallE_PEDIDO_CANTIDAD;
	private Long detallE_PEDIDO_PRECIO;
	private Long ordeN_PEDIDO_ID;
	private Long productO_ID;
}


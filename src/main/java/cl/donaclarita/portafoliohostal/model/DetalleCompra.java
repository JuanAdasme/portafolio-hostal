package cl.donaclarita.portafoliohostal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DetalleCompra {
	private Long detallE_COMPRA_ID;
	private Long ordeN_COMPRA_ID;
	private Long habitacioN_NRO;
	private Long huespeD_RUT;
}


package cl.donaclarita.portafoliohostal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DetalleRecepcion {
	private int detallE_RECEPCION_ID;
	private int detallE_R_CANTIDAD;
	private int detallE_R_PRECIO;
	private int recepcioN_PRODUCTO_ID;
	private int productO_ID;
}
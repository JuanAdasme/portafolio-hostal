package cl.donaclarita.portafoliohostal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
	private Long productO_ID;
	private Long productO_CODIGO;
	private String productO_NOMBRE;
	private String productO_DESCRIPCION;
	private Long productO_STOCK;
}

package cl.donaclarita.portafoliohostal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Proveedor {
	private Long proveedoR_ID;
	private String proveedoR_NOMBRE;
	private int proveedoR_TELEFONO;
	private String proveedoR_EMAIL;
	private Long rubrO_ID;
}

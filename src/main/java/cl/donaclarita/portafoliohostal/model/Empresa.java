package cl.donaclarita.portafoliohostal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {
	private Long empresA_RUT;
	private String empresA_RUT_DV;
	private String empresA_NOMBRE;
	private String empresA_DIRECCION;
	private String empresA_EMAIL;
}

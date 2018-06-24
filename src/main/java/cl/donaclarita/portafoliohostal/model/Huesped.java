package cl.donaclarita.portafoliohostal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Huesped {
	private Long huespeD_RUT;
	private String huespeD_RUT_DV;
	private String huespeD_NOMBRE;
	private String huespeD_EMAIL;
	private Long empresA_RUT;
}

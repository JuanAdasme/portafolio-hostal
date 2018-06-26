package cl.donaclarita.portafoliohostal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Habitacion {
	private Long habitacioN_NRO;
	private Long habitacioN_DISPONIBLE;
	private Long tipO_HABITACION_ID;
}
package cl.donaclarita.portafoliohostal.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Factura {
	private Long facturA_ID;
	private Long facturA_VALOR_TOTAL;
	private Date facturA_FECHA;
	private Long empresA_RUT;
}
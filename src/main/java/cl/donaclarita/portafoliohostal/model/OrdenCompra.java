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
public class OrdenCompra {
	private int id;
	private Date fechaInicio;
	private Date fechaTermino;
	private int empresaRut;
}

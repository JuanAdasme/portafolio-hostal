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
public class RecepcionProducto {
	private int id;
	private int precioTotal;
	private Date fecha;
	private int empleadoRut;
	private int proveedorId;
}

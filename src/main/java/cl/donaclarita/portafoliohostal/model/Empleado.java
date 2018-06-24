package cl.donaclarita.portafoliohostal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
	
	private Long empleadO_RUT;
	private String empleadO_RUT_DV;
	private String empleadO_NOMBRE;
	private String empleadO_DIRECCION;
	private Long empleadO_TELEFONO;

//	@Override
//	public String toString() {
//		return "Empleado [rut=" + rut + ", rutDv=" + rutDv + ", nombre=" + nombre + ", dirección=" + direccion + "teléfono=" + telefono + "]";
//	}
}

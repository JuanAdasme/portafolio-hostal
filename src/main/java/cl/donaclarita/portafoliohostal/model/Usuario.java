package cl.donaclarita.portafoliohostal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	private String usuariO_NOMBRE_USUARIO;
	private String usuariO_CLAVE;
	private String usuariO_ROL;
	private String usuariO_NOMBRE;
	private String usuariO_APELLIDO_P;
	private String usuariO_APELLIDO_M;
	
}

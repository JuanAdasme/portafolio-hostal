package cl.donaclarita.portafoliohostal.model;

import lombok.Data;

@Data
public class Habitacion {
	private int numero;
	private int disponible;
	private int tipoHabitacionId;
}

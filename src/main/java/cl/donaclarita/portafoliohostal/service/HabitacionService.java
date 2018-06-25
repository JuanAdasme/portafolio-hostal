package cl.donaclarita.portafoliohostal.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import cl.donaclarita.portafoliohostal.model.Habitacion;


public class HabitacionService {
	private final static String SERVICE_URL = "http://localhost:62383/api/";
	private final static Logger LOGGER = Logger.getLogger(RestClient.class.getName());
	private final static String MSG_SERVICE_ERROR = "Problemas con el servicio rest.";
	
	public List<Habitacion> findAllHabitacions() {
		List<Habitacion> Habitacions = new ArrayList<Habitacion>();
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Habitacion[]> response = restTemplate.exchange(SERVICE_URL + "Habitacions",
					HttpMethod.GET, null, Habitacion[].class);	

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				LOGGER.log(Level.INFO, response.getBody()[0].toString());
				Habitacions.addAll(Arrays.asList(response.getBody()));
			}
			else {
				LOGGER.log(Level.INFO, "Response null or Status not OK");
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		
		return Habitacions;
	}
	
	public boolean crearHabitacion(Habitacion Habitacion) {

		boolean result = false;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<Habitacion> request = new HttpEntity<Habitacion>(Habitacion);
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "Habitacions", HttpMethod.POST,
					request, Boolean.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				result = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}

		return result;
	}
	
	public Habitacion getById(Long id) {
		Habitacion Habitacion = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Habitacion> response = restTemplate.exchange(SERVICE_URL + "Habitacions/" + id, HttpMethod.GET,
					null, Habitacion.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				Habitacion = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		return Habitacion;
	}
	
	public boolean edit(Habitacion Habitacion) {
		boolean result = false;
		LOGGER.log(Level.INFO, "Habitacion: " + Habitacion.getHabitacion_RUT(), Habitacion);
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<Habitacion> request = new HttpEntity<Habitacion>(Habitacion);
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "Habitacions/" + Habitacion.getHabitacion_RUT(), HttpMethod.PUT,
					request, Boolean.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				result = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		return result;
	}
	
	public boolean delete(Long rut) {

		boolean result = false;
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "Habitacions/" + rut, HttpMethod.DELETE,
					null, Boolean.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				result = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}

		return result;
	}
}

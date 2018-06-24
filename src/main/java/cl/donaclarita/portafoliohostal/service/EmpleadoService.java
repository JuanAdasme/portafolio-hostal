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

import cl.donaclarita.portafoliohostal.model.Empleado;


public class EmpleadoService {
	private final static String SERVICE_URL = "http://localhost:62383/api/";
	private final static Logger LOGGER = Logger.getLogger(RestClient.class.getName());
	private final static String MSG_SERVICE_ERROR = "Problemas con el servicio rest.";
	
	public List<Empleado> findAllEmpleados() {
		List<Empleado> empleados = new ArrayList<Empleado>();
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Empleado[]> response = restTemplate.exchange(SERVICE_URL + "empleadoes",
					HttpMethod.GET, null, Empleado[].class);	

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				LOGGER.log(Level.INFO, response.getBody()[0].toString());
				empleados.addAll(Arrays.asList(response.getBody()));
			}
			else {
				LOGGER.log(Level.INFO, "Response null or Status not OK");
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		
		return empleados;
	}
	
	public boolean crearEmpleado(Empleado empleado) {

		boolean result = false;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<Empleado> request = new HttpEntity<Empleado>(empleado);
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "empleadoes", HttpMethod.POST,
					request, Boolean.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				result = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}

		return result;
	}
	
	public Empleado getById(Long id) {
		Empleado empleado = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Empleado> response = restTemplate.exchange(SERVICE_URL + "empleadoes/" + id, HttpMethod.GET,
					null, Empleado.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				empleado = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		return empleado;
	}
	
	public boolean edit(Empleado empleado) {
		boolean result = false;
		LOGGER.log(Level.INFO, "Empleado: " + empleado.getEmpleadO_RUT(), empleado);
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<Empleado> request = new HttpEntity<Empleado>(empleado);
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "empleadoes/" + empleado.getEmpleadO_RUT(), HttpMethod.PUT,
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
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "empleadoes/" + rut, HttpMethod.DELETE,
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

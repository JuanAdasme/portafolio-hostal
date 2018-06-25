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

import cl.donaclarita.portafoliohostal.model.DetalleRecepcion;


public class DetalleRecepcionService {
	private final static String SERVICE_URL = "http://localhost:62383/api/";
	private final static Logger LOGGER = Logger.getLogger(RestClient.class.getName());
	private final static String MSG_SERVICE_ERROR = "Problemas con el servicio rest.";
	
	public List<DetalleRecepcion> findAllDetalleRecepcions() {
		List<DetalleRecepcion> DetalleRecepcions = new ArrayList<DetalleRecepcion>();
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<DetalleRecepcion[]> response = restTemplate.exchange(SERVICE_URL + "DetalleRecepcion",
					HttpMethod.GET, null, DetalleRecepcion[].class);	

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				LOGGER.log(Level.INFO, response.getBody()[0].toString());
				DetalleRecepcions.addAll(Arrays.asList(response.getBody()));
			}
			else {
				LOGGER.log(Level.INFO, "Response null or Status not OK");
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		
		return DetalleRecepcions;
	}
	
	public boolean crearDetalleRecepcion(DetalleRecepcion DetalleRecepcion) {

		boolean result = false;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<DetalleRecepcion> request = new HttpEntity<DetalleRecepcion>(DetalleRecepcion);
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "DetalleRecepcion", HttpMethod.POST,
					request, Boolean.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				result = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}

		return result;
	}
	
	public DetalleRecepcion getById(Long id) {
		DetalleRecepcion DetalleRecepcion = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<DetalleRecepcion> response = restTemplate.exchange(SERVICE_URL + "DetalleRecepcion/" + id, HttpMethod.GET,
					null, DetalleRecepcion.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				DetalleRecepcion = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		return DetalleRecepcion;
	}
	
	public boolean edit(DetalleRecepcion DetalleRecepcion) {
		boolean result = false;
		LOGGER.log(Level.INFO, "DetalleRecepcion: " + DetalleRecepcion.getDetalleRecepcion_RUT(), DetalleRecepcion);
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<DetalleRecepcion> request = new HttpEntity<DetalleRecepcion>(DetalleRecepcion);
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "DetalleRecepcion/" + DetalleRecepcion.getDetalleRecepcion_RUT(), HttpMethod.PUT,
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
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "DetalleRecepcion/" + rut, HttpMethod.DELETE,
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

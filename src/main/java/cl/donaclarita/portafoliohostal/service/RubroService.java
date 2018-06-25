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

import cl.donaclarita.portafoliohostal.model.Rubro;


public class RubroService {
	private final static String SERVICE_URL = "http://localhost:62383/api/";
	private final static Logger LOGGER = Logger.getLogger(RestClient.class.getName());
	private final static String MSG_SERVICE_ERROR = "Problemas con el servicio rest.";
	
	public List<Rubro> findAllRubros() {
		List<Rubro> Rubros = new ArrayList<Rubro>();
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Rubro[]> response = restTemplate.exchange(SERVICE_URL + "Rubroes",
					HttpMethod.GET, null, Rubro[].class);	

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				LOGGER.log(Level.INFO, response.getBody()[0].toString());
				Rubros.addAll(Arrays.asList(response.getBody()));
			}
			else {
				LOGGER.log(Level.INFO, "Response null or Status not OK");
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		
		return Rubros;
	}
	
	public boolean crearRubro(Rubro Rubro) {

		boolean result = false;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<Rubro> request = new HttpEntity<Rubro>(Rubro);
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "Rubroes", HttpMethod.POST,
					request, Boolean.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				result = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}

		return result;
	}
	
	public Rubro getById(Long id) {
		Rubro Rubro = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Rubro> response = restTemplate.exchange(SERVICE_URL + "Rubroes/" + id, HttpMethod.GET,
					null, Rubro.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				Rubro = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		return Rubro;
	}
	
	public boolean edit(Rubro Rubro) {
		boolean result = false;
		LOGGER.log(Level.INFO, "Rubro: " + Rubro.getRubro_RUT(), Rubro);
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<Rubro> request = new HttpEntity<Rubro>(Rubro);
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "Rubroes/" + Rubro.getRubro_RUT(), HttpMethod.PUT,
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
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "Rubroes/" + rut, HttpMethod.DELETE,
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

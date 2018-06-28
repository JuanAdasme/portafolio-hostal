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

import cl.donaclarita.portafoliohostal.model.Empresa;
import cl.donaclarita.portafoliohostal.model.Huesped;

public class HuespedService {
	private final static String SERVICE_URL = "http://localhost:62383/api/";
	private final static Logger LOGGER = Logger.getLogger(RestClient.class.getName());
	private final static String MSG_SERVICE_ERROR = "Problemas con el servicio rest.";
	
	public List<Huesped> findAllHuespedes() {
		List<Huesped> huespedes = new ArrayList<Huesped>();
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Huesped[]> response = restTemplate.exchange(SERVICE_URL + "HUESPEDs",
					HttpMethod.GET, null, Huesped[].class);	

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				LOGGER.log(Level.INFO, response.getBody()[0].toString());
				huespedes.addAll(Arrays.asList(response.getBody()));
			}
			else {
				LOGGER.log(Level.INFO, "Response null or Status not OK");
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		
		return huespedes;
	}
	
	public List<Huesped> findByEmpresa(Long rutEmpresa) {
		List<Huesped> huespedes = new ArrayList<Huesped>();
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Huesped[]> response = restTemplate.exchange(SERVICE_URL + "HUESPEDs",
					HttpMethod.GET, null, Huesped[].class);	

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				LOGGER.log(Level.INFO, response.getBody()[0].toString());
				huespedes.addAll(Arrays.asList(response.getBody()));
			}
			else {
				LOGGER.log(Level.INFO, "Response null or Status not OK");
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		
		return huespedes;
	}
	
	public Huesped crearHuesped(Huesped huesped) {

		Huesped result = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<Huesped> request = new HttpEntity<Huesped>(huesped);
			ResponseEntity<Huesped> response = restTemplate.exchange(SERVICE_URL + "HUESPEDs", HttpMethod.POST,
					request, Huesped.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				result = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}

		return result;
	}
}

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

import cl.donaclarita.portafoliohostal.model.DetalleCompra;


public class DetalleCompraService {
	private final static String SERVICE_URL = "http://localhost:62383/api/";
	private final static Logger LOGGER = Logger.getLogger(RestClient.class.getName());
	private final static String MSG_SERVICE_ERROR = "Problemas con el servicio rest.";
	
	public List<DetalleCompra> findAllDetalleCompras() {
		List<DetalleCompra> detalleCompras = new ArrayList<DetalleCompra>();
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<DetalleCompra[]> response = restTemplate.exchange(SERVICE_URL + "detalleCompra",
					HttpMethod.GET, null, DetalleCompra[].class);	

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				LOGGER.log(Level.INFO, response.getBody()[0].toString());
				detalleCompras.addAll(Arrays.asList(response.getBody()));
			}
			else {
				LOGGER.log(Level.INFO, "Response null or Status not OK");
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		
		return detalleCompras;
	}
	
	public boolean crearDetalleCompra(DetalleCompra DetalleCompra) {

		boolean result = false;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<DetalleCompra> request = new HttpEntity<DetalleCompra>(DetalleCompra);
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "detalleCompra", HttpMethod.POST,
					request, Boolean.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				result = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}

		return result;
	}
	
	public DetalleCompra getById(Long id) {
		DetalleCompra DetalleCompra = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<DetalleCompra> response = restTemplate.exchange(SERVICE_URL + "detalleCompra/" + id, HttpMethod.GET,
					null, DetalleCompra.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				DetalleCompra = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		return DetalleCompra;
	}
	
	public boolean edit(DetalleCompra detalleCompra) {
		boolean result = false;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<DetalleCompra> request = new HttpEntity<DetalleCompra>(detalleCompra);
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "detalleCompra/" + detalleCompra.getDetallE_COMPRA_ID(), HttpMethod.PUT,
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
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "detalleCompra/" + rut, HttpMethod.DELETE,
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

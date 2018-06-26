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

import cl.donaclarita.portafoliohostal.model.DetallePedido;


public class DetallePedidoService {
	private final static String SERVICE_URL = "http://localhost:62383/api/";
	private final static Logger LOGGER = Logger.getLogger(RestClient.class.getName());
	private final static String MSG_SERVICE_ERROR = "Problemas con el servicio rest.";
	
	public List<DetallePedido> findAllDetallePedidos() {
		List<DetallePedido> DetallePedidos = new ArrayList<DetallePedido>();
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<DetallePedido[]> response = restTemplate.exchange(SERVICE_URL + "DetallePedido",
					HttpMethod.GET, null, DetallePedido[].class);	

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				LOGGER.log(Level.INFO, response.getBody()[0].toString());
				DetallePedidos.addAll(Arrays.asList(response.getBody()));
			}
			else {
				LOGGER.log(Level.INFO, "Response null or Status not OK");
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		
		return DetallePedidos;
	}
	
	public boolean crearDetallePedido(DetallePedido DetallePedido) {

		boolean result = false;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<DetallePedido> request = new HttpEntity<DetallePedido>(DetallePedido);
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "DetallePedido", HttpMethod.POST,
					request, Boolean.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				result = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}

		return result;
	}
	
	public DetallePedido getById(Long id) {
		DetallePedido DetallePedido = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<DetallePedido> response = restTemplate.exchange(SERVICE_URL + "DetallePedido/" + id, HttpMethod.GET,
					null, DetallePedido.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				DetallePedido = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		return DetallePedido;
	}
	
	public boolean edit(DetallePedido detallePedido) {
		boolean result = false;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<DetallePedido> request = new HttpEntity<DetallePedido>(detallePedido);
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "DetallePedido/" + detallePedido.getDetallE_PEDIDO_ID(), HttpMethod.PUT,
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
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "DetallePedido/" + rut, HttpMethod.DELETE,
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

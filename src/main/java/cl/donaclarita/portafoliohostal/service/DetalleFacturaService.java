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

import cl.donaclarita.portafoliohostal.model.DetalleFactura;


public class DetalleFacturaService {
	private final static String SERVICE_URL = "http://localhost:62383/api/";
	private final static Logger LOGGER = Logger.getLogger(RestClient.class.getName());
	private final static String MSG_SERVICE_ERROR = "Problemas con el servicio rest.";
	
	public List<DetalleFactura> findAllDetalleFacturas() {
		List<DetalleFactura> DetalleFacturas = new ArrayList<DetalleFactura>();
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<DetalleFactura[]> response = restTemplate.exchange(SERVICE_URL + "detalleFactura",
					HttpMethod.GET, null, DetalleFactura[].class);	

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				LOGGER.log(Level.INFO, response.getBody()[0].toString());
				DetalleFacturas.addAll(Arrays.asList(response.getBody()));
			}
			else {
				LOGGER.log(Level.INFO, "Response null or Status not OK");
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		
		return DetalleFacturas;
	}
	
	public boolean crearDetalleFactura(DetalleFactura DetalleFactura) {

		boolean result = false;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<DetalleFactura> request = new HttpEntity<DetalleFactura>(DetalleFactura);
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "detalleFactura", HttpMethod.POST,
					request, Boolean.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				result = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}

		return result;
	}
	
	public DetalleFactura getById(Long id) {
		DetalleFactura DetalleFactura = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<DetalleFactura> response = restTemplate.exchange(SERVICE_URL + "detalleFactura/" + id, HttpMethod.GET,
					null, DetalleFactura.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				DetalleFactura = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		return DetalleFactura;
	}
	
	public boolean edit(DetalleFactura detalleFactura) {
		boolean result = false;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<DetalleFactura> request = new HttpEntity<DetalleFactura>(detalleFactura);
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "detalleFactura/" + detalleFactura.getDetallE_FACTURA_ID(), HttpMethod.PUT,
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
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "detalleFactura/" + rut, HttpMethod.DELETE,
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

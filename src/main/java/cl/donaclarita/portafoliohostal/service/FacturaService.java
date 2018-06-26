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

import cl.donaclarita.portafoliohostal.model.Factura;


public class FacturaService {
	private final static String SERVICE_URL = "http://localhost:62383/api/";
	private final static Logger LOGGER = Logger.getLogger(RestClient.class.getName());
	private final static String MSG_SERVICE_ERROR = "Problemas con el servicio rest.";
	
	public List<Factura> findAllFacturas() {
		List<Factura> Facturas = new ArrayList<Factura>();
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Factura[]> response = restTemplate.exchange(SERVICE_URL + "Facturas",
					HttpMethod.GET, null, Factura[].class);	

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				LOGGER.log(Level.INFO, response.getBody()[0].toString());
				Facturas.addAll(Arrays.asList(response.getBody()));
			}
			else {
				LOGGER.log(Level.INFO, "Response null or Status not OK");
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		
		return Facturas;
	}
	
	public boolean crearFactura(Factura factura) {

		boolean result = false;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<Factura> request = new HttpEntity<Factura>(factura);
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "Facturas", HttpMethod.POST,
					request, Boolean.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				result = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}

		return result;
	}
	
	public Factura getById(Long id) {
		Factura Factura = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Factura> response = restTemplate.exchange(SERVICE_URL + "Facturaes/" + id, HttpMethod.GET,
					null, Factura.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				Factura = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		return Factura;
	}
	
	public boolean edit(Factura factura) {
		boolean result = false;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<Factura> request = new HttpEntity<Factura>(factura);
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "Facturas/" + factura.getFacturA_ID(), HttpMethod.PUT,
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
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "Facturas/" + rut, HttpMethod.DELETE,
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

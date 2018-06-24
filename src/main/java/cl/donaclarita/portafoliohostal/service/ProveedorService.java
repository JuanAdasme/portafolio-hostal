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
import cl.donaclarita.portafoliohostal.model.Proveedor;

public class ProveedorService {
	private final static String SERVICE_URL = "http://localhost:62383/api/";
	private final static Logger LOGGER = Logger.getLogger(RestClient.class.getName());
	private final static String MSG_SERVICE_ERROR = "Problemas con el servicio rest.";
	
	public List<Proveedor> findAllProveedores() {
		List<Proveedor> proveedores = new ArrayList<Proveedor>();
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Proveedor[]> response = restTemplate.exchange(SERVICE_URL + "PROVEEDORs",
					HttpMethod.GET, null, Proveedor[].class);	

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				LOGGER.log(Level.INFO, response.getBody()[0].toString());
				proveedores.addAll(Arrays.asList(response.getBody()));
			}
			else {
				LOGGER.log(Level.INFO, "Response null or Status not OK");
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		
		return proveedores;
	}
	
	public Proveedor crearProveedor(Proveedor proveedor) {

		Proveedor result = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<Proveedor> request = new HttpEntity<Proveedor>(proveedor);
			ResponseEntity<Proveedor> response = restTemplate.exchange(SERVICE_URL + "proveedors", HttpMethod.POST,
					request, Proveedor.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				result = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}

		return result;
	}
}

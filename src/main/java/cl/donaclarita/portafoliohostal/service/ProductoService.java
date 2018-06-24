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
import cl.donaclarita.portafoliohostal.model.Producto;

public class ProductoService {
	private final static String SERVICE_URL = "http://localhost:62383/api/";
	private final static Logger LOGGER = Logger.getLogger(RestClient.class.getName());
	private final static String MSG_SERVICE_ERROR = "Problemas con el servicio rest.";
	
	public List<Producto> findAllProductos() {
		List<Producto> producto = new ArrayList<Producto>();
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Producto[]> response = restTemplate.exchange(SERVICE_URL + "productoes",
					HttpMethod.GET, null, Producto[].class);	

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				LOGGER.log(Level.INFO, response.getBody()[0].toString());
				producto.addAll(Arrays.asList(response.getBody()));
			}
			else {
				LOGGER.log(Level.INFO, "Response null or Status not OK");
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		
		return producto;
	}
	
	public Producto crearProducto(Producto producto) {

		Producto result = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<Producto> request = new HttpEntity<Producto>(producto);
			ResponseEntity<Producto> response = restTemplate.exchange(SERVICE_URL + "productoes", HttpMethod.POST,
					request, Producto.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				result = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}

		return result;
	}
}

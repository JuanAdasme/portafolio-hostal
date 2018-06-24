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
import cl.donaclarita.portafoliohostal.model.Usuario;

public class UsuarioService {
	private final static String SERVICE_URL = "http://localhost:62383/api/";
	private final static Logger LOGGER = Logger.getLogger(RestClient.class.getName());
	private final static String MSG_SERVICE_ERROR = "Problemas con el servicio rest.";
	
	public List<Usuario> findAllUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Usuario[]> response = restTemplate.exchange(SERVICE_URL + "usuarios",
					HttpMethod.GET, null, Usuario[].class);	

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				LOGGER.log(Level.INFO, response.getBody()[0].toString());
				usuarios.addAll(Arrays.asList(response.getBody()));
			}
			else {
				LOGGER.log(Level.INFO, "Response null or Status not OK");
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		
		return usuarios;
	}
	
	public boolean crearUsuario(Usuario usuario) {

		boolean result = false;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuario);
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "usuarios", HttpMethod.POST,
					request, Boolean.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				result = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}

		return result;
	}
}

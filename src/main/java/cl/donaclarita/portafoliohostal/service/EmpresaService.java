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
import cl.donaclarita.portafoliohostal.model.Empresa;

public class EmpresaService {
	private final static String SERVICE_URL = "http://localhost:62383/api/";
	private final static Logger LOGGER = Logger.getLogger(RestClient.class.getName());
	private final static String MSG_SERVICE_ERROR = "Problemas con el servicio rest.";
	
	public List<Empresa> findAllEmpresas() {
		List<Empresa> empresas = new ArrayList<Empresa>();
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Empresa[]> response = restTemplate.exchange(SERVICE_URL + "empresas",
					HttpMethod.GET, null, Empresa[].class);	

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				LOGGER.log(Level.INFO, response.getBody()[0].toString());
				empresas.addAll(Arrays.asList(response.getBody()));
			}
			else {
				LOGGER.log(Level.INFO, "Response null or Status not OK");
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		
		return empresas;
	}
	
	public Empresa crearEmpresa(Empresa empresa) {

		Empresa result = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<Empresa> request = new HttpEntity<Empresa>(empresa);
			ResponseEntity<Empresa> response = restTemplate.exchange(SERVICE_URL + "empresas", HttpMethod.POST,
					request, Empresa.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				result = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}

		return result;
	}
	
	public Empresa getById(Long id) {
		Empresa empresa = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Empresa> response = restTemplate.exchange(SERVICE_URL + "EMPRESAs/" + id, HttpMethod.GET,
					null, Empresa.class);

			if (response != null && response.getStatusCode() == HttpStatus.OK) {
				empresa = response.getBody();
			}

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, MSG_SERVICE_ERROR, e);
		}
		return empresa;
	}
	
	public boolean edit(Empresa empresa) {
		boolean result = false;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<Empresa> request = new HttpEntity<Empresa>(empresa);
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "EMPRESAs/" + empresa.getEmpresA_RUT(), HttpMethod.PUT,
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
			ResponseEntity<Boolean> response = restTemplate.exchange(SERVICE_URL + "EMPRESAs/" + rut, HttpMethod.DELETE,
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

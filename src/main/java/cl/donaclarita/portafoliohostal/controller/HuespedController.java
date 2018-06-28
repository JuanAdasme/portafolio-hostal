package cl.donaclarita.portafoliohostal.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cl.donaclarita.portafoliohostal.model.Huesped;
import cl.donaclarita.portafoliohostal.service.HuespedService;

@Controller
public class HuespedController {
	private final static Logger LOGGER = Logger.getLogger(HuespedController.class.getName());
	HuespedService restClient = new HuespedService();
	
	@GetMapping("/cliente/huespedes/huespedes")
	public void listByEmpresa(Model model) {
		List<Huesped> huespedes = restClient.findAllHuespedes();
		model.addAttribute("huespedes", huespedes);	
	}
	
	@GetMapping("/admin/huespedes/add")
	public String agregar() {
		return "/admin/huespedes/add";
	}
	
	@PostMapping("/admin/huespedes/add")
	public void agregar(Long rut, String dv, String nombre, String email, Long rutEmpresa) {
		Huesped huesped = new Huesped(rut, dv, nombre, email, rutEmpresa);
		restClient.crearHuesped(huesped);
	}
}

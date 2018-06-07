package cl.donaclarita.portafoliohostal.controller.admin;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cl.donaclarita.portafoliohostal.model.Usuario;
import cl.donaclarita.portafoliohostal.service.UsuarioService;

@Controller
public class UsuarioController {
	
	private final static Logger LOGGER = Logger.getLogger(EmpleadoController.class.getName());
	UsuarioService restClient = new UsuarioService();
	
	@GetMapping("/admin/empleados/list")
	public void listarTodos(Model model) {
		List<Usuario> usuarios = restClient.findAllEmpleados();
		model.addAttribute("usuarios", usuarios);
	}
}

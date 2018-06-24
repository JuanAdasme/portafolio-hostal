package cl.donaclarita.portafoliohostal.controller.admin;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cl.donaclarita.portafoliohostal.model.Usuario;
import cl.donaclarita.portafoliohostal.service.UsuarioService;

@Controller
public class UsuarioController {
	
	private final static Logger LOGGER = Logger.getLogger(UsuarioController.class.getName());
	UsuarioService restClient = new UsuarioService();
	
	@GetMapping("/admin/usuarios/list")
	public void listarTodos(Model model) {
		List<Usuario> usuarios = restClient.findAllUsuarios();
		model.addAttribute("usuarios", usuarios);
	}
	
	@GetMapping("/admin/usuarios/add")
	public void agregar() {
	}
	
	@PostMapping("/admin/usuarios/add")
	public void agregar(String username, String clave, String rol, String nombre, String apellidoPaterno, String apellidoMaterno) {
		Usuario usuario = new Usuario(username, clave, rol, nombre, apellidoPaterno, apellidoMaterno);
		restClient.crearUsuario(usuario);
	}
}

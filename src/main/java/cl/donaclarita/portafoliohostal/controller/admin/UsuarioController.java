package cl.donaclarita.portafoliohostal.controller.admin;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public void agregar(String username, String clave, String rol, String nombre, String apellidoPaterno,
			String apellidoMaterno) {
		Usuario usuario = new Usuario(username, clave, rol, nombre, apellidoPaterno, apellidoMaterno);
		restClient.crearUsuario(usuario);
	}

	@GetMapping("/admin/usuarios/edit")
	public void editar(String id, Model model) {
		Usuario usuario = restClient.getById(id);
		model.addAttribute("usuario", usuario);
	}

	@PostMapping("/admin/usuarios/edit")
	public String editar(String username, String nombre, String apellidoPaterno, String apellidoMaterno) {
		Usuario usuario = restClient.getById(username);
		usuario.setUsuariO_NOMBRE(nombre);
		usuario.setUsuariO_APELLIDO_P(apellidoPaterno);
		usuario.setUsuariO_APELLIDO_M(apellidoMaterno);
		restClient.edit(usuario);
		return "/admin/usuarios/list";
	}

	@RequestMapping("/admin/usuarios/delete")
	public String delete(String username, Model model) {
		LOGGER.log(Level.WARNING, "Eliminar usuario: " + username, username);
		boolean es = restClient.delete(username);
		return "/admin/usuarios/list";
	}
}

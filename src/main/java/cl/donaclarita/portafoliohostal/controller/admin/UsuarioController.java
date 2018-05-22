package cl.donaclarita.portafoliohostal.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {
	
	@GetMapping("admin/usuarios/list")
	public String listUsers() {
		return "admin/usuarios/list";
	}
}

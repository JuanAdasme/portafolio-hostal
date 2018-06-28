package cl.donaclarita.portafoliohostal.controller.admin;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.donaclarita.portafoliohostal.model.Proveedor;
import cl.donaclarita.portafoliohostal.model.Usuario;
import cl.donaclarita.portafoliohostal.service.ProveedorService;

@Controller
public class ProveedorController {
	private final static Logger LOGGER = Logger.getLogger(ProveedorController.class.getName());
	ProveedorService restClient = new ProveedorService();
	
	@GetMapping("/admin/proveedores/list")
	public void listarTodos(Model model) {
		List<Proveedor> proveedores = restClient.findAllProveedores();
		model.addAttribute("proveedores", proveedores);	
	}
	
	@GetMapping("/admin/proveedores/add")
	public String agregar() {
		return "/admin/proveedores/add";
	}
	
	@PostMapping("/admin/proveedores/add")
	public void agregar(Long id, String nombre, Long telefono, String email, Long rubro) {
		Proveedor proveedor = new Proveedor(id, nombre, telefono, email, rubro);
		restClient.crearProveedor(proveedor);
	}
	
	@GetMapping("/admin/proveedores/edit")
	public void editar(Long id, Model model) {
		Proveedor proveedor = restClient.getById(id);
		model.addAttribute("proveedor", proveedor);
	}

	@PostMapping("/admin/proveedores/edit")
	public String editar(Long id, String nombre, Long telefono, String email, Long rubro) {
		Proveedor proveedor= restClient.getById(id);
		proveedor.setProveedoR_NOMBRE(nombre);
		proveedor.setProveedoR_TELEFONO(telefono);
		proveedor.setProveedoR_EMAIL(email);
		proveedor.setRubrO_ID(rubro);
		restClient.edit(proveedor);
		return "/admin/proveedores/list";
	}
	
	@RequestMapping("/admin/proveedores/delete")
	public String delete(Long id, Model model) {
		boolean es = restClient.delete(id);
		return "/admin/proveedores/list";
	}
}

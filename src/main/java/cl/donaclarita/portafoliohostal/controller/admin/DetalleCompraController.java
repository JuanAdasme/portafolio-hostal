		package cl.donaclarita.portafoliohostal.controller.admin;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.donaclarita.portafoliohostal.model.DetalleCompra;
import cl.donaclarita.portafoliohostal.service.DetalleCompraService;

@Controller
public class DetalleCompraController {
	
	private final static Logger LOGGER = Logger.getLogger(DetalleCompraController.class.getName());
	DetalleCompraService restClient = new DetalleCompraService();
	
	@GetMapping("/admin/detalleCompras/list")
	public void listarTodos(Model model) {
		List<DetalleCompra> detalleCompras = restClient.findAllDetalleCompras();
		model.addAttribute("detalleCompras", detalleCompras);	
	}
	
	@GetMapping("/admin/detalleCompras/add")
	public String agregar() {
		return "/admin/detalleCompras/add";
	}
	
	@PostMapping("/admin/detalleCompras/add")
	public void agregar(Long id, Long huespedeRut, Long habitacionId, Long ordenCompraId) {
		DetalleCompra detalleCompra = new DetalleCompra(id, huespedeRut, habitacionId, ordenCompraId);
		restClient.crearDetalleCompra(detalleCompra);
	}
	
	@GetMapping("/admin/detalleCompras/edit")
	public void editar(Long id, Model model) {
		DetalleCompra detalleCompra = restClient.getById(id);
		model.addAttribute("detalleCompra", detalleCompra);
	}
	
	@PostMapping("/admin/detalleCompras/edit")
	public String editar(Long id, Long huespedeRut, Long habitacionId, Long ordenCompraId) {
		DetalleCompra detalleCompra = new DetalleCompra(id, huespedeRut, habitacionId, ordenCompraId);
		restClient.edit(detalleCompra);
		return "/admin/detalleCompras/list";
	}
	
	@RequestMapping("/admin/detalleCompras/delete")////////////////////////////////// CORREGIR LA PARTE DE USUARIO
	public void delete(Long rut, Model model) {
		boolean es = restClient.delete(rut);
		if(es) {
			model.addAttribute("msg", "Usuario eliminado");
		}
		else {
			model.addAttribute("msg", "Usuario NO eliminado");
		}
	}
	
	@GetMapping("/admin/detalleCompras/grafico")
	public String chart() {
		return "/admin/detalleCompras/grafico";
	}
}

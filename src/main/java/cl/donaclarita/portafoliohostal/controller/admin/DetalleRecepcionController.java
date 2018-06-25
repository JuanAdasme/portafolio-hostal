		package cl.donaclarita.portafoliohostal.controller.admin;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.donaclarita.portafoliohostal.model.DetalleRecepcion;
import cl.donaclarita.portafoliohostal.service.DetalleRecepcionService;

@Controller
public class DetalleRecepcionController {
	
	private final static Logger LOGGER = Logger.getLogger(DetalleRecepcionController.class.getName());
	DetalleRecepcionService restClient = new DetalleRecepcionService();
	
	@GetMapping("/admin/DetalleRecepcions/list")
	public void listarTodos(Model model) {
		List<DetalleRecepcion> detalleRecepcions = restClient.findAllDetalleRecepcions();
		model.addAttribute("detalleRecepcions", detalleRecepcions);	
	}
	
	@GetMapping("/admin/DetalleRecepcions/add")
	public String agregar() {
		return "/admin/DetalleRecepcions/add";
	}
	
	@PostMapping("/admin/DetalleRecepcions/add")
	public void agregar(int id, int cantidad, int precio, int recepcionId, int productoId) {
		DetalleRecepcion detalleRecepcion = new DetalleRecepcion(id, cantidad, precio, recepcionId, productoId);
		restClient.crearDetalleRecepcion(detalleRecepcion);
	}
	
	@GetMapping("/admin/DetalleRecepcions/edit")
	public void editar(Long id, Model model) {
		DetalleRecepcion detalleRecepcion = restClient.getById(id);
		model.addAttribute("detalleRecepcion", detalleRecepcion);
	}
	
	@PostMapping("/admin/DetalleRecepcions/edit")
	public String editar(int id, int cantidad, int precio, int recepcionId, int productoId) {
		DetalleRecepcion detalleRecepcion = new DetalleRecepcion(id, cantidad, precio, recepcionId, productoId);
		restClient.edit(detalleRecepcion);
		return "/admin/DetalleRecepcions/list";
	}
	
	@RequestMapping("/admin/DetalleRecepcions/delete")
	public void delete(Long rut, Model model) {
		boolean es = restClient.delete(rut);
		if(es) {
			model.addAttribute("msg", "Usuario eliminado");
		}
		else {
			model.addAttribute("msg", "Usuario NO eliminado");
		}
	}
	
	@GetMapping("/admin/DetalleRecepcions/grafico")
	public String chart() {
		return "/admin/DetalleRecepcions/grafico";
	}
}

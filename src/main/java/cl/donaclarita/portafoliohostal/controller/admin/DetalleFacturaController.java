		package cl.donaclarita.portafoliohostal.controller.admin;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.donaclarita.portafoliohostal.model.DetalleFactura;
import cl.donaclarita.portafoliohostal.service.DetalleFacturaService;

@Controller
public class DetalleFacturaController {
	
	private final static Logger LOGGER = Logger.getLogger(DetalleFacturaController.class.getName());
	DetalleFacturaService restClient = new DetalleFacturaService();
	
	@GetMapping("/admin/DetalleFacturas/list")
	public void listarTodos(Model model) {
		List<DetalleFactura> detalleFacturas = restClient.findAllDetalleFacturas();
		model.addAttribute("detalleFacturas", detalleFacturas);	
	}
	
	@GetMapping("/admin/DetalleFacturas/add")
	public String agregar() {
		return "/admin/DetalleFacturas/add";
	}
	
	@PostMapping("/admin/DetalleFacturas/add")
	public void agregar(int id, int precio, int estadia, int facturaId, int menuId, int habitacionNumero) {
		DetalleFactura detalleFactura = new DetalleFactura(id, precio, estadia, facturaId, menuId, habitacionNumero);
		restClient.crearDetalleFactura(detalleFactura);
	}
	
	@GetMapping("/admin/DetalleFacturas/edit")
	public void editar(Long id, Model model) {
		DetalleFactura detalleFactura = restClient.getById(id);
		model.addAttribute("detalleFactura", detalleFactura);
	}
	
	@PostMapping("/admin/DetalleFacturas/edit")
	public String editar(int id, int precio, int estadia, int facturaId, int menuId, int habitacionNumero) {
		DetalleFactura detalleFactura = new DetalleFactura(id, precio, estadia, facturaId, menuId, habitacionNumero);
		restClient.edit(detalleFactura);
		return "/admin/DetalleFacturas/list";
	}
	
	@RequestMapping("/admin/DetalleFacturas/delete")
	public void delete(Long rut, Model model) {
		boolean es = restClient.delete(rut);
		if(es) {
			model.addAttribute("msg", "Usuario eliminado");
		}
		else {
			model.addAttribute("msg", "Usuario NO eliminado");
		}
	}
	
	@GetMapping("/admin/DetalleFacturas/grafico")
	public String chart() {
		return "/admin/DetalleFacturas/grafico";
	}
}

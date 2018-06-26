		package cl.donaclarita.portafoliohostal.controller.admin;

import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.donaclarita.portafoliohostal.model.Factura;
import cl.donaclarita.portafoliohostal.service.FacturaService;

@Controller
public class FacturaController {
	
	private final static Logger LOGGER = Logger.getLogger(FacturaController.class.getName());
	FacturaService restClient = new FacturaService();
	
	@GetMapping("/admin/Facturas/list")
	public void listarTodos(Model model) {
		List<Factura> facturas = restClient.findAllFacturas();
		model.addAttribute("facturas", facturas);	
	}
	
	@GetMapping("/admin/Facturas/add")
	public String agregar() {
		return "/admin/Facturas/add";
	}
	
	@PostMapping("/admin/Facturas/add")
	public void agregar(Long id, Long valorTotal, Date fecha, Long empresaRut) {
		Factura factura = new Factura(id, valorTotal, fecha, empresaRut);
		restClient.crearFactura(factura);
	}
	
	@GetMapping("/admin/Facturas/edit")
	public void editar(Long id, Model model) {
		Factura factura = restClient.getById(id);
		model.addAttribute("factura", factura);
	}
	
	@PostMapping("/admin/Facturas/edit")
	public String editar(Long id, Long valorTotal, Date fecha, Long empresaRut) {
		Factura factura = new Factura(id, valorTotal, fecha, empresaRut);
		restClient.edit(factura);
		return "/admin/Facturas/list";
	}
	
	@RequestMapping("/admin/Facturas/delete")
	public void delete(Long rut, Model model) {
		boolean es = restClient.delete(rut);
		if(es) {
			model.addAttribute("msg", "Usuario eliminado");
		}
		else {
			model.addAttribute("msg", "Usuario NO eliminado");
		}
	}
	
	@GetMapping("/admin/Facturas/grafico")
	public String chart() {
		return "/admin/Facturas/grafico";
	}
}

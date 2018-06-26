		package cl.donaclarita.portafoliohostal.controller.admin;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.donaclarita.portafoliohostal.model.DetallePedido;
import cl.donaclarita.portafoliohostal.service.DetallePedidoService;

@Controller
public class DetallePedidoController {
	
	private final static Logger LOGGER = Logger.getLogger(DetallePedidoController.class.getName());
	DetallePedidoService restClient = new DetallePedidoService();
	
	@GetMapping("/admin/DetallePedidos/list")
	public void listarTodos(Model model) {
		List<DetallePedido> detallePedidos = restClient.findAllDetallePedidos();
		model.addAttribute("detallePedidos", detallePedidos);	
	}
	
	@GetMapping("/admin/DetallePedidos/add")
	public String agregar() {
		return "/admin/DetallePedidos/add";
	}
	
	@PostMapping("/admin/DetallePedidos/add")
	public void agregar(Long id, Long cantidad, Long precio, Long ordenPedidoId, Long proveedorId) {
		DetallePedido detallePedido = new DetallePedido(id, cantidad, precio, ordenPedidoId, proveedorId);
		restClient.crearDetallePedido(detallePedido);
	}
	
	@GetMapping("/admin/DetallePedidos/edit")
	public void editar(Long id, Model model) {
		DetallePedido detallePedido = restClient.getById(id);
		model.addAttribute("detallePedido", detallePedido);
	}
	
	@PostMapping("/admin/DetallePedidos/edit")
	public String editar(Long id, Long cantidad, Long precio, Long ordenPedidoId, Long proveedorId) {
		DetallePedido detallePedido = new DetallePedido(id, cantidad, precio, ordenPedidoId, proveedorId);
		restClient.edit(detallePedido);
		return "/admin/DetallePedidos/list";
	}
	
	@RequestMapping("/admin/DetallePedidos/delete")
	public void delete(Long rut, Model model) {
		boolean es = restClient.delete(rut);
		if(es) {
			model.addAttribute("msg", "Usuario eliminado");
		}
		else {
			model.addAttribute("msg", "Usuario NO eliminado");
		}
	}
	
	@GetMapping("/admin/DetallePedidos/grafico")
	public String chart() {
		return "/admin/DetallePedidos/grafico";
	}
}

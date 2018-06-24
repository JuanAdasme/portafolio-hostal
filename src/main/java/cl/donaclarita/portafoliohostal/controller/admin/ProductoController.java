package cl.donaclarita.portafoliohostal.controller.admin;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cl.donaclarita.portafoliohostal.model.Empresa;
import cl.donaclarita.portafoliohostal.model.Producto;
import cl.donaclarita.portafoliohostal.service.ProductoService;

@Controller
public class ProductoController {
	private final static Logger LOGGER = Logger.getLogger(ProductoController.class.getName());
	ProductoService restClient = new ProductoService();
	
	@GetMapping("/admin/productos/list")
	public void listarTodos(Model model) {
		List<Producto> productos = restClient.findAllProductos();
		model.addAttribute("productos", productos);	
	}
	
	@GetMapping("/admin/productos/add")
	public String agregar() {
		return "/admin/productos/add";
	}
	
	@PostMapping("/admin/productos/add")
	public void agregar(Long id, Long codigo, String nombre, String descripcion, Long stock) {
		Producto producto = new Producto(id, codigo, nombre, descripcion, stock);
		restClient.crearProducto(producto);
	}
}

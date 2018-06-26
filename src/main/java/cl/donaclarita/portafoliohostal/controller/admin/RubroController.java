		package cl.donaclarita.portafoliohostal.controller.admin;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.donaclarita.portafoliohostal.model.Rubro;
import cl.donaclarita.portafoliohostal.service.RubroService;

@Controller
public class RubroController {
	
	private final static Logger LOGGER = Logger.getLogger(RubroController.class.getName());
	RubroService restClient = new RubroService();
	
	@GetMapping("/admin/rubros/list")
	public void listarTodos(Model model) {
		List<Rubro> rubros = restClient.findAllRubros();
		model.addAttribute("rubros", rubros);	
	}
	
	@GetMapping("/admin/rubros/add")
	public String agregar() {
		return "/admin/Rubros/add";
	}
	
	@PostMapping("/admin/rubros/add")
	public void agregar(Long id, String nombre) {
		Rubro rubro = new Rubro(id, nombre);
		restClient.crearRubro(rubro);
	}
	
	@GetMapping("/admin/rubros/edit")
	public void editar(Long id, Model model) {
		Rubro rubro = restClient.getById(id);
		model.addAttribute("rubro", rubro);
	}
	
	@PostMapping("/admin/rubros/edit")
	public String editar(Long id, String nombre) {
		Rubro rubro = new Rubro(id, nombre);
		restClient.edit(rubro);
		return "/admin/Rubros/list";
	}
	
	@RequestMapping("/admin/rubros/delete")
	public void delete(Long rut, Model model) {
		boolean es = restClient.delete(rut);
		if(es) {
			model.addAttribute("msg", "Rubro eliminado");
		}
		else {
			model.addAttribute("msg", "Rubro NO eliminado");
		}
	}
	
}

		package cl.donaclarita.portafoliohostal.controller.admin;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.donaclarita.portafoliohostal.model.Habitacion;
import cl.donaclarita.portafoliohostal.service.HabitacionService;

@Controller
public class HabitacionController {
	
	private final static Logger LOGGER = Logger.getLogger(HabitacionController.class.getName());
	HabitacionService restClient = new HabitacionService();
	
	@GetMapping("/admin/Habitacions/list")
	public void listarTodos(Model model) {
		List<Habitacion> habitacions = restClient.findAllHabitacions();
		model.addAttribute("habitacions", habitacions);	
	}
	
	@GetMapping("/admin/Habitacions/add")
	public String agregar() {
		return "/admin/Habitacions/add";
	}
	
	@PostMapping("/admin/Habitacions/add")
	public void agregar(int numero, int disponible, int tipoHabitacionId) {
		Habitacion habitacion = new Habitacion(numero, disponible, tipoHabitacionId);
		restClient.crearHabitacion(habitacion);
	}
	
	@GetMapping("/admin/Habitacions/edit")
	public void editar(Long id, Model model) {
		Habitacion habitacion = restClient.getById(id);
		model.addAttribute("habitacion", habitacion);
	}
	
	@PostMapping("/admin/Habitacions/edit")
	public String editar(int numero, int disponible, int tipoHabitacionId) {
		Habitacion habitacion = new Habitacion(numero, disponible, tipoHabitacionId);
		restClient.edit(habitacion);
		return "/admin/Habitacions/list";
	}
	
	@RequestMapping("/admin/Habitacions/delete")
	public void delete(Long rut, Model model) {
		boolean es = restClient.delete(rut);
		if(es) {
			model.addAttribute("msg", "Usuario eliminado");
		}
		else {
			model.addAttribute("msg", "Usuario NO eliminado");
		}
	}
	
	@GetMapping("/admin/Habitacions/grafico")
	public String chart() {
		return "/admin/Habitacions/grafico";
	}
}

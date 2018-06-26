package cl.donaclarita.portafoliohostal.controller.admin;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.donaclarita.portafoliohostal.model.Empleado;
import cl.donaclarita.portafoliohostal.service.EmpleadoService;

@Controller
public class EmpleadoController {

	private final static Logger LOGGER = Logger.getLogger(EmpleadoController.class.getName());
	EmpleadoService restClient = new EmpleadoService();

	@GetMapping("/admin/empleados/list")
	public void listarTodos(Model model) {
		List<Empleado> empleados = restClient.findAllEmpleados();
		model.addAttribute("empleados", empleados);
	}

	@GetMapping("/admin/empleados/add")
	public String agregar() {
		return "/admin/empleados/add";
	}

	@PostMapping("/admin/empleados/add")
	public void agregar(Long rut, String dv, String nombre, String direccion, Long telefono) {
		Empleado empleado = new Empleado(rut, dv, nombre, direccion, telefono);
		restClient.crearEmpleado(empleado);
	}

	@GetMapping("/admin/empleados/edit")
	public void editar(Long id, Model model) {
		Empleado empleado = restClient.getById(id);
		model.addAttribute("empleado", empleado);
	}

	@PostMapping("/admin/empleados/edit")
	public String editar(Long rut, String dv, String nombre, String direccion, Long telefono) {
		Empleado empleado = new Empleado(rut, dv, nombre, direccion, telefono);
		restClient.edit(empleado);
		return "/admin/empleados/list";
	}

	@RequestMapping("/admin/empleados/delete")
	public String delete(Long id, Model model) {
		boolean es = restClient.delete(id);
		return "/admin/empleados/list";
	}

}

package cl.donaclarita.portafoliohostal.controller.admin;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.donaclarita.portafoliohostal.model.Empleado;
import cl.donaclarita.portafoliohostal.model.Empresa;
import cl.donaclarita.portafoliohostal.service.EmpresaService;

@Controller
public class EmpresaController {
	private final static Logger LOGGER = Logger.getLogger(EmpresaController.class.getName());
	EmpresaService restClient = new EmpresaService();
	
	@GetMapping("/admin/empresas/list")
	public void listarTodos(Model model) {
		List<Empresa> empresas = restClient.findAllEmpresas();
		model.addAttribute("empresas", empresas);	
	}
	
	@GetMapping("/admin/empresas/add")
	public String agregar() {
		return "/admin/empresas/add";
	}
	
	@PostMapping("/admin/empresas/add")
	public void agregar(Long rut, String dv, String nombre, String direccion, String email) {
		Empresa empresa = new Empresa(rut, dv, nombre, direccion, email);
		restClient.crearEmpresa(empresa);
	}
	
	@GetMapping("/admin/empresas/edit")
	public void editar(Long id, Model model) {
		Empresa empresa = restClient.getById(id);
		model.addAttribute("empresa", empresa);
	}
	
	@PostMapping("/admin/empresas/edit")
	public String editar(Long rut, String dv, String nombre, String direccion, String email) {
		Empresa empresa = new Empresa(rut, dv, nombre, direccion, email);
		restClient.edit(empresa);
		return "/admin/empresas/list";
	}
	
	@RequestMapping("/admin/empresas/delete")
	public String delete(Long rut, Model model) {
		boolean es = restClient.delete(rut);
		return "/admin/empresas/list";
	}
}

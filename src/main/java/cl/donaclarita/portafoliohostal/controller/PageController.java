package cl.donaclarita.portafoliohostal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
	
	@GetMapping("/")
	public String root() {
		return "index";
	}
	
	@GetMapping("/index")
	public void index() {
	}
	
	@GetMapping("/error")
	public String error() {
		return "error";
	}
	
	@GetMapping("/admin/index")
	public void admin() {
	}
	
	@GetMapping("/admin")
	public String adminRoot() {
		return "/admin/index";
	}
	
	@GetMapping("/login")
	public void login() {
	}
	
	@GetMapping("/habitaciones")
	public void habitaciones() {
	}
	
	@GetMapping("/comedor")
	public void comedor() {
	}
	
	@GetMapping("/cliente/index")
	public void cliente() {
	}
	
	@GetMapping("/cliente")
	public String clienteRoot() {
		return "/cliente/index";
	}
	
	@GetMapping("/cliente/huespedes")
	public String clienteHuespedesRoot() {
		return "/cliente/huespedes/huespedes";
	}
	
	@GetMapping("/cliente/huespedes/add")
	public void clienteAgregarHuespedes() {
	}
}

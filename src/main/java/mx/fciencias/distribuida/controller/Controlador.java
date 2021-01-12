package mx.fciencias.distribuida.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;

import mx.fciencias.distribuida.model.Usuario;
import mx.fciencias.distribuida.repo.UsuarioRep;

@Controller
public class Controlador {
	@Autowired
	private UsuarioRep usuario_bd;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/registro")
	public String registro() {
		return "registro";
	}

	@RequestMapping("/iniciar-sesion")
	public String iniciar_sesion() {
		return "iniciar_sesion";
	}

	@RequestMapping("/log/inicio")
	public String principal() {
		return "log/inicio";
	}

	@PostMapping("/registrarse")
	public String registrarse(Model model, HttpServletRequest request) {
		String nombre = request.getParameter("nombre");
		String apellido1 = request.getParameter("apellido1");
		String apellido2 = request.getParameter("apellido2");
		String password = new BCryptPasswordEncoder().encode(request.getParameter("password"));
		String correo = request.getParameter("correo");
		int edad = Integer.parseInt(request.getParameter("edad"));
		String telefono = request.getParameter("telefono");
		String cp = request.getParameter("cp");
		String sexo = request.getParameter("sexo");

		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setApellido_paterno(apellido1);
		usuario.setApellido_materno(apellido2);
		usuario.setCorreo(correo);
		usuario.setPassword(password);
		usuario.setEdad(edad);
		usuario.setNum_telefonico(telefono);
		usuario.setCp(cp);
		usuario.setSexo(sexo);
		if (usuario_bd.existsByCorreo(correo)) {
			model.addAttribute("error", true);
			// error
			return "registro";
		}
		usuario_bd.save(usuario);

		String nombre_c = nombre + " " + apellido1 + " " + apellido2;
		model.addAttribute("nombre", nombre_c);
		return "exito";
	}

	@RequestMapping(value = "/salir", method =

	{ RequestMethod.GET, RequestMethod.POST })

	public String saliendo(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		for (Cookie cookie : request.getCookies()) {
			cookie.setMaxAge(0);
		}
		return "iniciar-sesion";
	}
}

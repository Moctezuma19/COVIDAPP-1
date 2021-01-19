package mx.fciencias.distribuida.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.fciencias.distribuida.model.Contacto;
import mx.fciencias.distribuida.model.Notificacion;
import mx.fciencias.distribuida.model.Usuario;
import mx.fciencias.distribuida.repo.ContactoRep;
import mx.fciencias.distribuida.repo.NotificacionRep;
import mx.fciencias.distribuida.repo.UsuarioRep;

@Controller
public class Controlador {
	@Autowired
	private UsuarioRep usuario_bd;

	@Autowired
	private ContactoRep contacto_bd;
	
	@Autowired
	private NotificacionRep notificacion_bd;

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
	public String principal(Model model, Principal principal) {
		float pcg = 0.0f;
		Usuario usuario = usuario_bd.findByCorreo(principal.getName());
		ArrayList<Contacto> contactos = new ArrayList<>(usuario.getContactos());
		if (contactos.size() == 0) {
			model.addAttribute("lleno", false);
		} else {

			for (Contacto c : contactos) {
				if (c.getId_usuario_c() != null) {
					Usuario aux = usuario_bd.findById(c.getId_usuario_c().getId()).get();
					c.setNombre(aux.getNombre() + " " + aux.getApellido_paterno() + " " + aux.getApellido_materno());
					c.setEdad(aux.getEdad());
					c.setPcg(0.0f);
					c.setPct(0.0f);
					c.setSexo(aux.getSexo());
				}
			}
			for (Contacto c : contactos) {
				pcg += c.getPcg() * c.getPct();
			}
			pcg /= contactos.size();
			String txt = "";
			String color = "";
			if (pcg < 0.25f) {
				txt = "Estas lejos de contagiarte pero manten precauciones.";
				color = "green";
			} else if (pcg >= 0.25f && pcg <= 0.5f) {
				txt = "Es un volado contagiarte, pon mas atencion";
				color = "orange";
			} else {
				txt = "Es muy probable que te contagies, sera mejor no tener contacto con alguien";
				color = "red";
			}
			model.addAttribute("lleno", true);
			model.addAttribute("contactos", contactos);
			model.addAttribute("pcg", pcg);
			model.addAttribute("pcgtxt", txt);
			model.addAttribute("color", color);
		}

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

	@PostMapping("/log/agregacontacto")
	public String agregaContacto(Model model, HttpServletRequest request, Principal principal) {
		Contacto c = new Contacto();
		Usuario usuario = usuario_bd.findByCorreo(principal.getName());
		c.setId_usuario(usuario);
		if (!request.getParameter("uid").equals("0")) {
			Usuario user = usuario_bd.findById(Integer.parseInt(request.getParameter("uid"))).get();
			c.setId_usuario_c(user);
			contacto_bd.save(c);

			Contacto c2 = new Contacto();
			c2.setId_usuario_c(usuario);
			c2.setId_usuario(user);
			contacto_bd.save(c2);
			return "redirect:/log/inicio";

		}
		String nombre = request.getParameter("nombre");
		int edad = Integer.parseInt(request.getParameter("edad"));
		String sexo = request.getParameter("sexo");
		float pct = Float.parseFloat(request.getParameter("pct"));
		float pcg = Float.parseFloat(request.getParameter("pcg"));

		c.setNombre(nombre);
		c.setEdad(edad);
		c.setSexo(sexo);
		c.setPct(pct);
		c.setPcg(pcg);

		contacto_bd.save(c);
		return "redirect:/log/inicio";
	}

	@ResponseBody
	@PostMapping("/log/busqueda")
	public Map<String, String> busquedaUsuario(HttpServletRequest request) {
		HashMap<String, String> map = new HashMap<>();
		if (!usuario_bd.existsByCorreo(request.getParameter("correo"))) {
			map.put("msg", "error");
			return map;
		}
		Usuario u = usuario_bd.findByCorreo(request.getParameter("correo"));
		map.put("uid", String.valueOf(u.getId()));
		map.put("msg", "ok");
		map.put("nombre", u.getNombre() + " " + u.getApellido_paterno() + " " + u.getApellido_materno());
		map.put("edad", String.valueOf(u.getEdad()));
		map.put("sexo", u.getSexo());
		map.put("pct", String.valueOf(u.getPct()));
		map.put("pcg", String.valueOf(u.getPcg()));
		return map;
	}

	@GetMapping("/log/notifica")
	public String notificar(HttpServletRequest request,Principal principal) {
		Usuario usuario = usuario_bd.findByCorreo(principal.getName());
		ArrayList<Contacto> contactos = new ArrayList<>(usuario.getContactos());
		for(Contacto c : contactos) {
			System.out.println("pase");
			if(c.getId_usuario_c() != null) {		
				Notificacion n = new Notificacion();
				n.setId_usuario(usuario);
				n.setLeido(false);
				n.setTipo(request.getParameter("status").equals("1"));
				n.setTiempo_creado(new Date().getTime());
				n.setId_usuario_c(c.getId_usuario_c());
				notificacion_bd.save(n);
			}
		}
		
		return "redirect:/log/inicio";
	}
	
	@ResponseBody
	@PostMapping("/log/obtennotificaciones")
	public Map<String,String> obten_notificaciones(HttpServletRequest request,Principal principal) {
		Usuario usuario = usuario_bd.findByCorreo(principal.getName());
		ArrayList<Notificacion> notificaciones = new ArrayList<>(usuario.getNotificados());
		HashMap<String, String> map = new HashMap<>();
		if(notificaciones.size()==0) {
			map.put("msg","error");
			return map;
		}
		
		int i = 0;
		for(Notificacion n : notificaciones) {
			map.put("leido" + i, (n.isLeido())?"1":"0");
			map.put("tipo" + i, (n.isTipo())?"1":"0");
			Timestamp ts = new Timestamp(n.getTiempo_creado());
			map.put("fecha" + i,ts.toString());
			map.put("contacto" + i,n.getId_usuario().getNombre() + " " + n.getId_usuario().getApellido_paterno() + " " + n.getId_usuario().getApellido_materno());
			i++;
			n.setLeido(true);
			notificacion_bd.save(n);//ya se va a leer
		}
		map.put("longitud", String.valueOf(i));
		map.put("msg","ok");
		return map;
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

package mx.fciencias.distribuida.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mx.fciencias.distribuida.model.Usuario;
import mx.fciencias.distribuida.repo.UsuarioRep;

@Service
public class MyAppUserDetailsService implements UserDetailsService {
	@Autowired
	private UsuarioRep usuarioSys;

	@Override
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
		Usuario activeUserInfo = usuarioSys.findByCorreo(correo);// .get(0);

		/*
		 * if (activeUserInfo != null) { if
		 * (activeUserInfo.getFk_id_estatus_usuario_sys().getNombre().equals("Inactivo")
		 * ) { activeUserInfo = null; } }
		 */

		UserDetails userDetails;
		GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getNombre());
		userDetails = (UserDetails) new User(activeUserInfo.getCorreo(), activeUserInfo.getPassword(),
				Arrays.asList(authority));

		return userDetails;
	}
}

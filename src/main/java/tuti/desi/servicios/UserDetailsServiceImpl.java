package tuti.desi.servicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tuti.desi.accesoDatos.IUsuarioRepository;
import tuti.desi.entidades.Usuario;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Usuario user = (Usuario) usuarioRepository.findByUsername(username);

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encoded = bCryptPasswordEncoder.encode(username);

		return user;

		// si el Usuario no implementara UserDetails hay que hacer esto:
//   	 return new org.springframework.security.core.userdetails.User(user.getUsername(), 
//    		user.getPassword(), user.isEnabled(), true, true,  true, getAuthorities(user.getRol()));

	}

	private Collection<? extends GrantedAuthority> getAuthorities(String rol) {

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(rol));

		return authorities;
	}

}
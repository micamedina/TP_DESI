package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import tuti.desi.entidades.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{

	UserDetails findByUsername(String username);

}

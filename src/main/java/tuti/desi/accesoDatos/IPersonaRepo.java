package tuti.desi.accesoDatos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tuti.desi.entidades.Persona;

@Repository
public interface IPersonaRepo extends JpaRepository<Persona, Long> {
	List<Persona> findByNombreOrDni( String nombre, Long dni);

	@Query("SELECT p FROM Persona p WHERE p.nombre like ?1 or p.dni=?2 or p.ciudad.id=?3")
	List<Persona> findByNombreOrIdCiudad(String nombre, Long dni, Long idProvinciaSeleccionada);
}

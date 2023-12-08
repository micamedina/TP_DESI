package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import tuti.desi.entidades.Avion;

import java.util.Optional;

public interface IAvionRepo extends JpaRepository<Avion, Long> {

    Optional<Avion> findByNombre(String nombre);

    
}

package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import tuti.desi.entidades.Vuelo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IVueloRepo extends JpaRepository<Vuelo, Long> {

	Optional<Vuelo> findByNroVuelo(String nroVuelo);
	
	List<Vuelo> findByAvionIdAndFechaHoraPartidaBetween(Long avionId, LocalDateTime start, LocalDateTime end);
	
	boolean existsByAvionIdAndFechaHoraPartidaBetween(
            Long avionId,
            LocalDateTime fechaHoraPartidaDesde,
            LocalDateTime fechaHoraPartidaHasta
    );

    
}

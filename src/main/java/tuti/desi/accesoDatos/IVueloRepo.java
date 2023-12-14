package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tuti.desi.entidades.Ciudad;
import tuti.desi.entidades.Vuelo;

import java.time.LocalDate;
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

	List<Vuelo> findByOrigenAndDestinoAndTipoVueloAndFechaHoraPartidaBetween(Ciudad origen, Ciudad destino,
			String tipoVuelo, LocalDateTime atStartOfDay, LocalDateTime atTime);

	List<Vuelo> findByOrigenAndDestinoAndFechaHoraPartidaBetween(Ciudad origen, Ciudad destino,
			LocalDateTime atStartOfDay, LocalDateTime atTime);

	List<Vuelo> findByOrigenAndFechaHoraPartidaBetween(Ciudad origen, LocalDateTime atStartOfDay, LocalDateTime atTime);

	List<Vuelo> findByFechaHoraPartidaBetween(LocalDateTime atStartOfDay, LocalDateTime atTime);

	    
}

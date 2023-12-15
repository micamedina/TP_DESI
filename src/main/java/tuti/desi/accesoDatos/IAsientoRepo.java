package tuti.desi.accesoDatos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tuti.desi.entidades.Asiento;

public interface IAsientoRepo extends JpaRepository<Asiento, Long> {
	
    List<Asiento> findByVueloId(Long idVuelo);

    @Modifying
    @Query("UPDATE Asiento a SET a.disponible = :disponible WHERE a.id = :idAsiento")
    void actualizarDisponibilidadAsiento(@Param("idAsiento") Long idAsiento, @Param("disponible") boolean disponible);
    
    Optional<Asiento> findById(Long id);
    
    List<Asiento> findAllByVueloIdAndDisponibleNot(Long idVuelo, boolean disponible);

}

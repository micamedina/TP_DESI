package tuti.desi.servicios;

import java.util.List;

import tuti.desi.entidades.Asiento;

public interface AsientoService {
	
    void guardarAsiento(Asiento asiento);

    List<Asiento> findByVueloId(Long idVuelo);
    
    void actualizarDisponibilidadAsiento(Long idAsiento, boolean disponible);
    
    Asiento findById(Long idAsiento);

}

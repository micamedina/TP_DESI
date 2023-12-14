package tuti.desi.servicios;

import tuti.desi.entidades.Ciudad;
import tuti.desi.entidades.Vuelo;
import java.time.LocalDate;
import java.util.List;

public interface VueloService {

    List <Vuelo> obtenerTodosLosVuelos();

    Vuelo obtenerVueloPorId(Long id);

    void guardarVuelo(Vuelo vuelo);
    
    boolean existeNumeroVuelo(String numeroVuelo);
    
    public boolean existeVueloFechaAvion(Vuelo vuelo);
    
    List <Vuelo> filter(Ciudad origen, Ciudad destino, String tipoVuelo, LocalDate fecha);

}

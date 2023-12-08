package tuti.desi.servicios;

import tuti.desi.entidades.Vuelo;

import java.util.List;

public interface VueloService {

    List<Vuelo> obtenerTodosLosVuelos();

    Vuelo obtenerVueloPorId(Long id);

    void guardarVuelo(Vuelo vuelo);
    
    boolean existeNumeroVuelo(String numeroVuelo);
    
    public boolean existeVueloFechaAvion(Vuelo vuelo);

    // Puedes agregar otros métodos según tus necesidades
}

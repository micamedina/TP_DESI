package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.entidades.Vuelo;
import tuti.desi.accesoDatos.IVueloRepo;

import java.util.List;

@Service
public class VueloServiceImpl implements VueloService {

    @Autowired
    private IVueloRepo vueloRepositorio;

    @Override
    public List<Vuelo> obtenerTodosLosVuelos() {
        return vueloRepositorio.findAll();
    }

    @Override
    public Vuelo obtenerVueloPorId(Long id) {
        return vueloRepositorio.findById(id).orElse(null);
    }

    @Override
    public void guardarVuelo(Vuelo vuelo) {
        vueloRepositorio.save(vuelo);
    }
    
    @Override
    public boolean existeVueloFechaAvion(Vuelo vuelo) {
        return vueloRepositorio.existsByAvionIdAndFechaHoraPartidaBetween(
                vuelo.getAvion().getId(),
                vuelo.getFechaHoraPartida().toLocalDate().atStartOfDay(),
                vuelo.getFechaHoraPartida().toLocalDate().atTime(23, 59, 59)
        );
    }
    
    @Override
    public boolean existeNumeroVuelo(String numeroVuelo) {
        return vueloRepositorio.findByNroVuelo(numeroVuelo).isPresent();
    }

    
}

package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.entidades.Ciudad;
import tuti.desi.entidades.Vuelo;
import tuti.desi.excepciones.Excepcion;
import tuti.desi.presentacion.cuidades.CiudadesBuscarForm;
import tuti.desi.accesoDatos.IVueloRepo;

import java.time.LocalDate;
import java.util.List;

@Service
public class VueloServiceImpl implements VueloService {

	@Autowired
	private IVueloRepo vueloRepositorio;
	@Autowired
	private CiudadService ciudadService;


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
		return vueloRepositorio.existsByAvionIdAndFechaHoraPartidaBetween(vuelo.getAvion().getId(),
				vuelo.getFechaHoraPartida().toLocalDate().atStartOfDay(),
				vuelo.getFechaHoraPartida().toLocalDate().atTime(23, 59, 59));
	}

	@Override
	public boolean existeNumeroVuelo(String numeroVuelo) {
		return vueloRepositorio.findByNroVuelo(numeroVuelo).isPresent();
	}

	@Override
	public List <Vuelo> filter(Ciudad origen, Ciudad destino, String tipoVuelo, LocalDate fecha) {
		List <Vuelo> vuelos;
		
		if (origen != null && destino != null && tipoVuelo != null) {

			vuelos = vueloRepositorio.findByOrigenAndDestinoAndTipoVueloAndFechaHoraPartidaBetween(origen, destino, tipoVuelo,
					fecha.atStartOfDay(), fecha.atTime(23, 59, 59));
		} else if (origen != null && destino != null) {

			vuelos = vueloRepositorio.findByOrigenAndDestinoAndFechaHoraPartidaBetween(origen, destino, fecha.atStartOfDay(),
					fecha.atTime(23, 59, 59));
		} else if (origen != null) {

			vuelos = vueloRepositorio.findByOrigenAndFechaHoraPartidaBetween(origen, fecha.atStartOfDay(),
					fecha.atTime(23, 59, 59));
		} else {

			vuelos = vueloRepositorio.findByFechaHoraPartidaBetween(fecha.atStartOfDay(), fecha.atTime(23, 59, 59));
		}
		return vuelos;
	}


	

}

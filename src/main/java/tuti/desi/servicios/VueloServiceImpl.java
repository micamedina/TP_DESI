package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tuti.desi.entidades.Ciudad;
import tuti.desi.entidades.Vuelo;
import tuti.desi.excepciones.Excepcion;
import tuti.desi.presentacion.cuidades.CiudadesBuscarForm;
import tuti.desi.presentacion.vuelos.VuelosListarForm;
import tuti.desi.accesoDatos.IVueloRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
	public List<Vuelo> filter(VuelosListarForm filter) {

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha = dateFormat.parse(filter.getFecha());

			// Lógica para filtrar vuelos por fecha
		    List<Vuelo> vuelos = vueloRepositorio.findByFechaHoraPartidaBetween(
		            convertirAStartOfDay(fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()),
		            convertirAEndOfDay(fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));

		    // Lógica para filtrar por ciudad de origen, si se proporciona
		    if (filter.getCiudadOrigen() != null) {
		        vuelos = vuelos.stream()
		                .filter(vuelo -> vuelo.getOrigen().getId().equals(filter.getCiudadOrigen()))
		                .collect(Collectors.toList());
		    }

		    // Lógica para filtrar por ciudad de destino, si se proporciona
		    if (filter.getCiudadDestino() != null) {
		        vuelos = vuelos.stream()
		                .filter(vuelo -> vuelo.getDestino().getId().equals(filter.getCiudadDestino()))
		                .collect(Collectors.toList());
		    }

		    // Lógica para filtrar por tipo de vuelo, si se proporciona
		    if (filter.getTipoVuelo() != null && !filter.getTipoVuelo().isEmpty()) {
		        vuelos = vuelos.stream()
		                .filter(vuelo -> vuelo.getTipoVuelo().equalsIgnoreCase(filter.getTipoVuelo()))
		                .collect(Collectors.toList());
		    }

		    

			
			vuelos.sort(Comparator.comparing(Vuelo::getFechaHoraPartida));
			
			return vuelos;

		} catch (ParseException e) {
			
			return Collections.emptyList();
		}
		

	}

	public LocalDateTime convertirAStartOfDay(LocalDate fecha) {
		return fecha.atStartOfDay();
	}

	public LocalDateTime convertirAEndOfDay(LocalDate fecha) {
		return fecha.atTime(LocalTime.MAX);
	}

}

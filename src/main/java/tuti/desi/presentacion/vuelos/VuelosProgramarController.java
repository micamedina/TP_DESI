package tuti.desi.presentacion.vuelos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import tuti.desi.entidades.Vuelo;
import tuti.desi.entidades.Ciudad;
import tuti.desi.entidades.Provincia;
import tuti.desi.entidades.Asiento;
import tuti.desi.entidades.Avion;
import tuti.desi.servicios.AvionService;
import tuti.desi.servicios.CiudadService;
import tuti.desi.servicios.VueloService;
import tuti.desi.servicios.AsientoService;

import java.util.List;

@Controller
@RequestMapping("/vuelos")
public class VuelosProgramarController {

	@Autowired
	private VueloService vueloServicio;

	@Autowired
	private AvionService avionServicio;

	@Autowired
	private CiudadService ciudadServicio;

	@Autowired
	private AsientoService asientoService;
	
	@GetMapping("/programar")
	public String mostrarFormularioVuelo(Model model) {
		model.addAttribute("vuelo", new Vuelo());
		
		return "vuelosProgramar";
	}

	@ModelAttribute("ciudades")
	public List<Ciudad> getAllciudades() {
		return this.ciudadServicio.getAll();
	}
	@ModelAttribute("aviones")
	public List<Avion> getAllaviones() {
		return this.avionServicio.obtenerTodosLosAviones();
	}

	@PostMapping("/agregar")
	public String agregarVuelo(@ModelAttribute("vuelo") @Valid Vuelo vuelo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("ciudades", ciudadServicio.getAll());
			
			return "vuelosProgramar";
		}

		if (vueloServicio.existeNumeroVuelo(vuelo.getNroVuelo())) {
			result.rejectValue("nroVuelo", "error.vuelo", "El número de vuelo ya existe");
			model.addAttribute("ciudades", ciudadServicio.getAll());
			
			return "vuelosProgramar";
		}
		
		
	    if (vueloServicio.existeVueloFechaAvion(vuelo)) {
	        result.addError(new FieldError("vuelo", "fechaHoraPartida", "Ya existe un vuelo para este avión en la misma fecha"));
	        model.addAttribute("ciudades", ciudadServicio.getAll());
	        
	        return "vuelosProgramar";
	    }

		
	    vueloServicio.guardarVuelo(vuelo);
		int filas = vuelo.getAvion().getCantidadFilas();
		int asientosPorFila = vuelo.getAvion().getAsientosPorFila();
		int totalAsientos = filas*asientosPorFila;
		
	     for (int i = 1; i <= totalAsientos ; i++) {
	                Asiento asiento = new Asiento();
	                asiento.setVuelo(vuelo);
	                asiento.setNumero(i);
	                asiento.setDisponible(true);
	                asientoService.guardarAsiento(asiento);
	        
	        }
	     
	     vuelo.setAsientosDisponibles(totalAsientos);
	     vueloServicio.guardarVuelo(vuelo);
		
		return "vuelosProgramar";
	}
}
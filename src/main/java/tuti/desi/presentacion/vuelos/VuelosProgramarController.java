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
import tuti.desi.entidades.Avion;
import tuti.desi.servicios.AvionService;
import tuti.desi.servicios.CiudadService;
import tuti.desi.servicios.VueloService;

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

	@GetMapping("/programar")
	public String mostrarFormularioVuelo(Model model) {
		model.addAttribute("vuelo", new Vuelo());
		model.addAttribute("aviones", avionServicio.obtenerTodosLosAviones());
		return "vuelosProgramar";
	}

	@ModelAttribute("ciudades")
	public List<Ciudad> getAllciudades() {
		return this.ciudadServicio.getAll();
	}

	@PostMapping("/agregar")
	public String agregarVuelo(@ModelAttribute("vuelo") @Valid Vuelo vuelo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("ciudades", ciudadServicio.getAll());
			model.addAttribute("aviones", avionServicio.obtenerTodosLosAviones());
			return "vuelosProgramar";
		}

		if (vueloServicio.existeNumeroVuelo(vuelo.getNroVuelo())) {
			result.rejectValue("nroVuelo", "error.vuelo", "El número de vuelo ya existe");
			model.addAttribute("ciudades", ciudadServicio.getAll());
			model.addAttribute("aviones", avionServicio.obtenerTodosLosAviones());
			return "vuelosProgramar";
		}
		
		
	    if (vueloServicio.existeVueloFechaAvion(vuelo)) {
	        result.addError(new FieldError("vuelo", "fechaHoraPartida", "Ya existe un vuelo para este avión en la misma fecha"));
	        model.addAttribute("ciudades", ciudadServicio.getAll());
	        model.addAttribute("aviones", avionServicio.obtenerTodosLosAviones());
	        return "vuelosProgramar";
	    }

		vueloServicio.guardarVuelo(vuelo);
		return "vuelosProgramar";
	}
}
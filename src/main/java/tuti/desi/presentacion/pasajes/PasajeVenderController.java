package tuti.desi.presentacion.pasajes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;
import tuti.desi.entidades.Persona;
import tuti.desi.entidades.Asiento;
import tuti.desi.entidades.Vuelo;

import tuti.desi.servicios.AsientoService;
import tuti.desi.servicios.PersonaService;
import tuti.desi.servicios.VueloService;

@Controller
@RequestMapping("/pasajes")
@SessionAttributes("persona")

public class PasajeVenderController {

	@Autowired
	private PersonaService personaServicio;

	@Autowired
	private VueloService vueloService;

	@Autowired
	private AsientoService asientoService;

	@ModelAttribute("persona")
	public Persona setUpPersona() {
		return new Persona();
	}

	@GetMapping("/vender")
	public String mostrarFormularioVender(Model model) {
		return "pasajesVender";
	}

	@PostMapping("/buscar")
	public String buscarPersona(@ModelAttribute("persona") @Valid Persona persona, BindingResult result, Model model)
			throws Exception {
		try {
			if (personaServicio.getPersonaById(persona.getDni()) == null) {
				result.addError(new FieldError("persona", "dni", "No existe una persona con este DNI"));
				model.addAttribute("personaEncontrada", false);
				return "pasajesVender";
			}

			model.addAttribute("vuelos", vueloService.obtenerTodosLosVuelos());
			model.addAttribute("personaEncontrada", true);
			model.addAttribute("persona", personaServicio.getPersonaById(persona.getDni()));
			return "pasajesVender";
		} catch (Exception e) {
			model.addAttribute("personaEncontrada", false);
			result.addError(new FieldError("persona", "dni", "No existe una persona con este DNI"));
			return "pasajesVender";
		}
	}
	
	   @PostMapping("/ver-asientos")
	    public String verAsientos(@RequestParam("idVuelo") Long idVuelo, Model model) {
	        Vuelo vuelo = vueloService.obtenerVueloPorId(idVuelo);
	        List<Asiento> asientos = asientoService.findByVueloId( idVuelo);

	        model.addAttribute("vuelo", vuelo);
	        model.addAttribute("asientos", asientos);

	        return "verAsientos";
	    }
	   
	   

}

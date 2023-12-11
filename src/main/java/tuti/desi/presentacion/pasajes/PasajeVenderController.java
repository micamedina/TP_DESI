package tuti.desi.presentacion.pasajes;

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
import tuti.desi.entidades.Persona;
import tuti.desi.servicios.PersonaService;
import tuti.desi.servicios.VueloService;

@Controller
@RequestMapping("/pasajes")
public class PasajeVenderController {
	
	@Autowired
	private PersonaService personaServicio;
	
	@Autowired
	private VueloService vueloService;
	
	@GetMapping("/vender")
	public String mostrarFormularioVender(Model model) {
		model.addAttribute("persona",new Persona());
		return "pasajesVender";
	}
	
	@PostMapping("/buscar")
	public String buscarPersona(@ModelAttribute("persona") @Valid Persona persona, BindingResult result, Model model) throws Exception {
		try { 
			if (personaServicio.getPersonaById(persona.getDni()) == null ) {
			    result.addError(new FieldError("persona", "dni", "No existe una persona con este DNI"));
				model.addAttribute("personaEncontrada",false);
			    return "pasajesVender";
			}
			 model.addAttribute("vuelos", vueloService.obtenerTodosLosVuelos());
			model.addAttribute("personaEncontrada",true);
			model.addAttribute("persona",personaServicio.getPersonaById(persona.getDni()));
			return "pasajesVender";
		} catch (Exception e) {
			model.addAttribute("personaEncontrada",false);
		    result.addError(new FieldError("persona", "dni", "No existe una persona con este DNI"));
			return "pasajesVender";
		}	
	}

}

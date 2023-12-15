package tuti.desi.presentacion.vuelos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import tuti.desi.entidades.Ciudad;
import tuti.desi.entidades.Vuelo;
import tuti.desi.presentacion.cuidades.CiudadesBuscarForm;
import tuti.desi.servicios.AsientoService;
import tuti.desi.servicios.CiudadService;
import tuti.desi.servicios.VueloService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/vuelos")
public class VuelosListarController {

	@Autowired
	private VueloService vueloService;
	
	@Autowired
	private AsientoService asientoService;

	@Autowired
	private CiudadService ciudadService;

	@GetMapping("/listar")
	public String listarVuelos(Model model) {

		VuelosListarForm form = new VuelosListarForm();

		model.addAttribute("formBean", form);

		return "vuelosBuscar";
	}

	@ModelAttribute("ciudades")
	public List<Ciudad> getAllciudades() {
		return this.ciudadService.getAll();
	}
	@ModelAttribute("vuelos")
	public List<Vuelo> getAllvuelos() {
		return this.vueloService.obtenerTodosLosVuelos();
	}

	@PostMapping("/mostrar")
	public String mostrarVuelosListadosYFiltrados(@ModelAttribute("formBean") @Valid VuelosListarForm formBean,
			BindingResult result, Model model) {

		if (result.hasErrors() || formBean.getFecha() == null) {
			result.rejectValue("fecha", "error.formBean", "La fecha es nula o inválida");
			model.addAttribute("errores", result.getAllErrors());
		} else {

			List<Vuelo> vuelos = vueloService.filter(formBean);
			model.addAttribute("resultados", vuelos);
			
			for (Vuelo vuelo : vuelos) {
				vuelo.setAsientosDisponibles(asientoService.countAsientosByVueloIdAndDisponibleNot(vuelo.getId()));
				vueloService.guardarVuelo(vuelo);
	            
	        }
			model.addAttribute("resultados", vuelos);
		}

		model.addAttribute("formBean", formBean);

		return "vuelosBuscar";

	}

}
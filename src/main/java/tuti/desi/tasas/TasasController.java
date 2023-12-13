package tuti.desi.tasas;

import tuti.desi.entidades.Tasa;
import tuti.desi.entidades.Vuelo;
import tuti.desi.servicios.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tasas")
public class TasasController {

	@Autowired
	private TasasServiceImp tasasService;

	@GetMapping("/mostrar")
	public String mostrarTasas(Model model) {
		List<Tasa> tasas = tasasService.obtenerTasas();

		if (!tasas.isEmpty()) {
			model.addAttribute("tasa", tasas.get(0));
		}
		return "tasasEditar";
	}

	@PostMapping("/editar")
	public String editarTasas(@ModelAttribute("tasa") @Valid Tasa tasa, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        
	        return "tasasEditar";
	    }

	    
	    Long tasaId = tasa.getId();
	    Tasa tasaExistente = tasasService.obtenerTasaPorId(tasaId);
	    
	    if (result.hasErrors()) {
	        model.addAttribute("error", "Por favor, corrija los errores en el formulario");
	        return "tasasEditar";
	    }
	    
	    if (tasaExistente != null) {
	        
	    } else {
	        model.addAttribute("error", "La tasa no se encontró");
	        return "tasasEditar";
	    }

	    
	    if (tasaExistente != null) {
	        
	        tasaExistente.setIva(tasa.getIva());
	        tasaExistente.setTasaNacional(tasa.getTasaNacional());
	        tasaExistente.setTasaInternacional(tasa.getTasaInternacional());
	        tasaExistente.setCotizacionDolar(tasa.getCotizacionDolar());
	        

	        
	        tasasService.guardarTasa(tasaExistente);
	    }

	    
	    List<Tasa> tasas = tasasService.obtenerTasas();

		if (!tasas.isEmpty()) {
			model.addAttribute("tasa", tasas.get(0));
		}
		return "tasasEditar";

	    
	}

}

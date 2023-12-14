package tuti.desi.presentacion.vuelos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tuti.desi.entidades.Ciudad;
import tuti.desi.entidades.Vuelo;
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
	private CiudadService ciudadService;

	
	@GetMapping("/listar")
	public String listarVuelos(Model model,
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
	        @RequestParam(required = false) Ciudad origen, @RequestParam(required = false) Ciudad destino,
	        @RequestParam(required = false) String tipoVuelo) {
		
		
		// Obtener los vuelos programados para la fecha, origen, destino y tipo de vuelo dados
	    List<Vuelo> vuelosProgramados = vueloService.obtenerVuelosProgramados(fecha, origen, destino, tipoVuelo);

	    

	    // Agregar vuelos y ciudades al modelo
	    model.addAttribute("vuelos", vuelosProgramados);
	
	    
	    
	    
		//model.addAttribute("vuelos", vueloService.obtenerTodosLosVuelos());

	    List<Ciudad> ciudades = ciudadService.getAll();
	    List<String> nombresCiudades = new ArrayList<>();
	    for (Ciudad ciudad : ciudades) {
	        nombresCiudades.add(ciudad.getNombre());
	    }
	    model.addAttribute("ciudades", nombresCiudades);
	    
	    return "listaVuelos";
	}

	
	
	@PostMapping("/mostrar")
	public String mostrarVuelosListadosYFiltrados(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
	                             @RequestParam(required = false) Ciudad origen,
	                             @RequestParam(required = false) Ciudad destino,
	                             @RequestParam(required = false) String tipoVuelo,
	                             Model model) {

	    List<Vuelo> vuelos = vueloService.filter(origen, destino, tipoVuelo, fecha);
	    model.addAttribute("vuelos", vuelos);

	    List<Ciudad> ciudades = ciudadService.getAll();
	    List<String> nombresCiudades = new ArrayList<>();
	    for (Ciudad ciudad : ciudades) {
	        nombresCiudades.add(ciudad.getNombre());
	    }
	    model.addAttribute("ciudades", nombresCiudades);

	    return "listaVuelos";
	}

	
	

}
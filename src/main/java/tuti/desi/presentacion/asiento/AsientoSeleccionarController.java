package tuti.desi.presentacion.asiento;
	
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import tuti.desi.entidades.Vuelo;
import tuti.desi.entidades.Asiento;
import tuti.desi.entidades.Persona;
import tuti.desi.entidades.Tasa;
import tuti.desi.servicios.AsientoService;
import tuti.desi.servicios.VueloService;
import tuti.desi.servicios.TasasService;

@Controller
@RequestMapping("/pasajes/ver-asientos")
public class AsientoSeleccionarController {

	@Autowired
	private AsientoService asientoService;

	@Autowired
	private VueloService vueloService;

	@Autowired
	private TasasService tasaService;

	@PostMapping("/ticket-compra")
	public String verAsientos(@RequestParam("idAsiento") Long idAsiento, Model model, HttpSession session) {
		Asiento asiento = asientoService.findById(idAsiento);
		asientoService.actualizarDisponibilidadAsiento(idAsiento, false);
		Vuelo vuelo = vueloService.obtenerVueloPorId(asiento.getVuelo().getId());
		Tasa tasa = tasaService.obtenerTasaPorId(1L);
        Persona persona = (Persona) session.getAttribute("persona");

		if (vuelo.getTipoVuelo().equalsIgnoreCase("Nacional")) {
			model.addAttribute("tasaPortuaria", tasa.getTasaNacional());
		} else {
			model.addAttribute("tasaPortuaria", tasa.getTasaInternacional() * tasa.getCotizacionDolar());
		}
		model.addAttribute("ivaPasaje", vuelo.getPrecioPasaje() * tasa.getIva() / 100);
		model.addAttribute("ciudadOrigen", vuelo.getOrigen());
		model.addAttribute("ciudadDestino", vuelo.getDestino());
		model.addAttribute("asiento", asiento);
		model.addAttribute("vuelo", vuelo);
		model.addAttribute("persona", persona);
		return "ticketCompra";
	}
}

package tuti.desi.presentacion.cuidades;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasas")
public class editarImpuestosController<tasasForm> {

    
    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        
        model.addAttribute("iva", 18.0); 
        model.addAttribute("tasaNacional", 50.0); 
        model.addAttribute("tasaInternacional", 20.0); 
        model.addAttribute("cotizacionDolar", 3.5); 

        return "formularioTasas";
    }

    @PostMapping("/guardar")
    public String guardarTasas(tasasForm tasasFormulario) {
        
        return "redirect:/tasas/formulario";
    }
}

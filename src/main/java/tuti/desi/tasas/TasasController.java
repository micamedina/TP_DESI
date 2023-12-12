package tuti.desi.tasas;

import tuti.desi.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TasasController {

    private  TasasServiceImp tasasService;

    @GetMapping("/tasas")
    public String mostrarTasas(Model model) {
        model.addAttribute("tasas", tasasService.obtenerTasas());
        return "tasas/lista";
    }
}

package tuti.desi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tuti.desi.accesoDatos.IAvionRepo;
import tuti.desi.entidades.Avion;

import java.util.List;

@Service
public class AvionServiceImpl implements AvionService {

    @Autowired
    private IAvionRepo avionRepo;

    @Override
    public List<Avion> obtenerTodosLosAviones() {
        return avionRepo.findAll();
    }

    @Override
    public Avion obtenerAvionPorId(Long id) {
        return avionRepo.findById(id).orElse(null);
    }

    @Override
    public void guardarAvion(Avion avion) {
        avionRepo.save(avion);
    }

    // Puedes implementar otros métodos según tus necesidades
}

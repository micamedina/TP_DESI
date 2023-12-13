package tuti.desi.servicios;

import tuti.desi.accesoDatos.TasaRepo;

import tuti.desi.entidades.Tasa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TasasServiceImp implements TasasService {
	
	@Autowired
    private TasaRepo tasas;
	
	@Override
	public List<Tasa> obtenerTasas(){
		 return tasas.findAll();	
		 
	}
	
	@Override
    public Tasa obtenerTasaPorId(Long id) {
        Optional<Tasa> optionalTasa = tasas.findById(id);
        return optionalTasa.orElse(null);
    }

	@Override
	public void guardarTasa(Tasa tasa) {
		tasas.save(tasa);
		
	}    
}

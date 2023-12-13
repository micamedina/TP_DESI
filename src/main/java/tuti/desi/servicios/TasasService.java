package tuti.desi.servicios;

import tuti.desi.entidades.Tasa;
import tuti.desi.entidades.Vuelo;

import java.util.List;

public interface TasasService {
	
	public List<Tasa> obtenerTasas();
	
	void guardarTasa(Tasa tasa);
	
	Tasa obtenerTasaPorId(Long id);


    
}

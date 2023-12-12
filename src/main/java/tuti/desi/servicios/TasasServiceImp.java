package tuti.desi.servicios;


import tuti.desi.accesoDatos.TasaRepo;
import tuti.desi.entidades.Tasa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class TasasServiceImp implements TasasService {
	
	@Autowired
    private TasaRepo tasas;
	
	@Override
	public List<Tasa> obtenerTasas(){
		 return tasas.findAll();	
		 
	}    
}

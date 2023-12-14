package tuti.desi.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tuti.desi.accesoDatos.IAsientoRepo;
import tuti.desi.entidades.Asiento;

@Service
public class AsientoServiceImpl implements AsientoService {

	@Autowired
	private IAsientoRepo asientoRepo;
	
	@Override
	public void guardarAsiento(Asiento asiento) {
		asientoRepo.save(asiento);
	}

	@Override
	public List<Asiento> findByVueloId(Long idVuelo) {
		return asientoRepo.findByVueloId(idVuelo);
	}

	@Transactional
	@Override
	public void actualizarDisponibilidadAsiento(Long idAsiento, boolean disponible) {
		asientoRepo.actualizarDisponibilidadAsiento(idAsiento, disponible);
		
	}

	@Override
	public Asiento findById(Long idAsiento) {
        return asientoRepo.findById(idAsiento).orElse(null);
	}


}

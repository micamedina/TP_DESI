package tuti.desi.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;

import tuti.desi.entidades.Tasa;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TasaRepo extends JpaRepository<Tasa, Long> {
	
	
	
}



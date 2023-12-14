package tuti.desi.servicios;

import tuti.desi.entidades.Avion;

import java.util.List;

public interface AvionService {

    List<Avion> obtenerTodosLosAviones();

    Avion obtenerAvionPorId(Long id);

    void guardarAvion(Avion avion);

}

package ar.edu.utn.frc.tup.lciii.templateSpring.services;

import ar.edu.utn.frc.tup.lciii.templateSpring.models.EquipoModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EquipoService {
    List<EquipoModel> getAllEquipos();
    EquipoModel getEquipo(Long id);
    EquipoModel actualizarEquipo(EquipoModel equipo);
}

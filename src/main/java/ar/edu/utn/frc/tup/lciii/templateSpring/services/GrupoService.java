package ar.edu.utn.frc.tup.lciii.templateSpring.services;

import ar.edu.utn.frc.tup.lciii.templateSpring.models.GrupoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.PartidoModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GrupoService {

    List<GrupoModel> crearGrupos();

    List<PartidoModel> crearPartidos();

    Boolean cerrarGrupos();
}

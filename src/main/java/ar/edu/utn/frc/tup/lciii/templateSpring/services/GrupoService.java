package ar.edu.utn.frc.tup.lciii.templateSpring.services;

import ar.edu.utn.frc.tup.lciii.templateSpring.models.GrupoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.PartidoModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GrupoService {

    List<GrupoModel> crearGrupos();

    List<PartidoModel> crearPartidos();

    List<PartidoModel> cerrarGrupos();
    void randomGrupos();
    GrupoModel getGrupoById(Long idGrupo);

    List<PartidoModel> cerrarCuartos();

    List<PartidoModel> cerrarSemis();

    List<PartidoModel> cerrarTercerPuesto();

    List<PartidoModel> cerrarFinal();
}

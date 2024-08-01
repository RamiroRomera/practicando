package ar.edu.utn.frc.tup.lciii.templateSpring.services;

import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.partido.InfoPartidoDto;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.EquipoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.PartidoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.utils.Etapa;
import org.springframework.stereotype.Service;

@Service
public interface PartidoService {
    PartidoModel getPartido(Long id);
    PartidoModel crearPartido(EquipoModel equipoLocal, EquipoModel equipoVisitante, Etapa etapa);
    PartidoModel actualizarPartido(PartidoModel partido);
    InfoPartidoDto terminarPartido(Long idPartido);
}

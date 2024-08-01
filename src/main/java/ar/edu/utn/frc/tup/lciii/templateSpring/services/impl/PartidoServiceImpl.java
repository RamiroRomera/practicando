package ar.edu.utn.frc.tup.lciii.templateSpring.services.impl;


import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.eventos.EventoDto;
import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.partido.InfoPartidoDto;
import ar.edu.utn.frc.tup.lciii.templateSpring.entities.PartidoEntity;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.EquipoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.PartidoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.utils.Etapa;
import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.PartidosRepository;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.EquipoService;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.EventoService;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.PartidoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartidoServiceImpl implements PartidoService {

    @Autowired
    private PartidosRepository partidoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private EventoService eventoService;

    @Override
    public PartidoModel getPartido(Long id) {
        return modelMapper.map(partidoRepository.findById(id), PartidoModel.class);
    }

    @Override
    public PartidoModel crearPartido(EquipoModel equipoLocal, EquipoModel equipoVisitante, Etapa etapa) {
        PartidoModel partido = new PartidoModel();
        partido.setEquipoLocal(equipoLocal);
        partido.setEquipoVisitante(equipoVisitante);
        partido.setGolesLocales(0);
        partido.setGolesVisitante(0);
        partido.setTerminado(false);
        partido.setEtapa(etapa);
        partido.setAmonestados(0);
        return modelMapper.map(partidoRepository.save(modelMapper.map(partido, PartidoEntity.class)), PartidoModel.class);
    }

    @Override
    public PartidoModel actualizarPartido(PartidoModel partido) {
        return modelMapper.map(partidoRepository.save(modelMapper.map(partido, PartidoEntity.class)), PartidoModel.class);
    }

    @Override
    public InfoPartidoDto terminarPartido(Long idPartido) {
        PartidoModel partido = this.getPartido(idPartido);
        partido.setTerminado(true);
        this.actualizarPartido(partido);

        if (partido.getGolesLocales() > partido.getGolesVisitante()) {
            partido.getEquipoLocal().setPuntos(partido.getEquipoLocal().getPuntos() + 3);
        } else if (partido.getGolesLocales() < partido.getGolesVisitante()) {
            partido.getEquipoVisitante().setPuntos(partido.getEquipoVisitante().getPuntos() + 3);
        } else {
            partido.getEquipoLocal().setPuntos(partido.getEquipoLocal().getPuntos() + 1);
            partido.getEquipoVisitante().setPuntos(partido.getEquipoVisitante().getPuntos() + 1);
        }

        equipoService.actualizarEquipo(partido.getEquipoLocal());
        equipoService.actualizarEquipo(partido.getEquipoVisitante());

        InfoPartidoDto infoPartido = modelMapper.map(partido, InfoPartidoDto.class);
        infoPartido.setEquipoLocal(partido.getEquipoLocal().getNombre());
        infoPartido.setEquipoVisitante(partido.getEquipoVisitante().getNombre());

        if (partido.getTerminado()) {
            infoPartido.setTerminado("Terminado");
        } else {
            infoPartido.setTerminado("No Terminado");
        }

        List<EventoDto> listEventosDto = new ArrayList<>();
        eventoService.getEventosByPartido(partido).forEach(eventoModel -> listEventosDto.add(modelMapper.map(eventoModel, EventoDto.class)));
        infoPartido.setListEventos(listEventosDto);

        return infoPartido;
    }
}

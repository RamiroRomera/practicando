package ar.edu.utn.frc.tup.lciii.templateSpring.services.impl;


import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.eventos.EventoDto;
import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.partido.InfoPartidoDto;
import ar.edu.utn.frc.tup.lciii.templateSpring.entities.EquipoEntity;
import ar.edu.utn.frc.tup.lciii.templateSpring.entities.PartidoEntity;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.EquipoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.GrupoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.PartidoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.utils.Etapa;
import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.PartidosRepository;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.EquipoService;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.EventoService;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.PartidoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public List<PartidoModel> getAllPartidoByGroup(GrupoModel grupo) {
        List<PartidoModel> listPartidos1 = new ArrayList<>();

        partidoRepository.findAllByEquipoLocal(modelMapper.map(grupo.getEquipos1(), EquipoEntity.class)).forEach(partido ->
            listPartidos1.add(modelMapper.map(partido, PartidoModel.class))
        );

        List<PartidoModel> listPartidos2 = new ArrayList<>();

        partidoRepository.findAllByEquipoLocal(modelMapper.map(grupo.getEquipos2(), EquipoEntity.class)).forEach(partido ->
                listPartidos2.add(modelMapper.map(partido, PartidoModel.class))
        );

        List<PartidoModel> listPartidos3 = new ArrayList<>();

        partidoRepository.findAllByEquipoLocal(modelMapper.map(grupo.getEquipos3(), EquipoEntity.class)).forEach(partido ->
                listPartidos3.add(modelMapper.map(partido, PartidoModel.class))
        );

        List<PartidoModel> listPartidos4 = new ArrayList<>();

        partidoRepository.findAllByEquipoLocal(modelMapper.map(grupo.getEquipos4(), EquipoEntity.class)).forEach(partido ->
                listPartidos4.add(modelMapper.map(partido, PartidoModel.class))
        );


        return getUniquePartidos(listPartidos1, listPartidos2, listPartidos3, listPartidos4);
    }

    private List<PartidoModel> getUniquePartidos(List<PartidoModel>... lists) {
        Set<PartidoModel> uniquePartidos = new HashSet<>();

        for (List<PartidoModel> list : lists) {
            uniquePartidos.addAll(list);
        }

        return uniquePartidos.stream().collect(Collectors.toList());
    }

    @Override
    public PartidoModel crearPartido(EquipoModel equipo, Integer llave, Etapa etapa) {
        Optional<PartidoEntity> partidoEntityOptional = (partidoRepository.findByLlaveAndEtapa(llave, etapa));
        PartidoModel partido;
        if (partidoEntityOptional.isEmpty()) {
            partido = new PartidoModel();
            partido.setEquipoLocal(equipo);
            partido.setGolesLocales(0);
            partido.setGolesVisitante(0);
            partido.setTerminado(false);
            partido.setEtapa(etapa);
            partido.setAmonestados(0);
            partido.setLlave(llave);
        } else {
            partido = modelMapper.map(partidoEntityOptional.get(), PartidoModel.class);
            partido.setEquipoVisitante(equipo);
        }
        return this.actualizarPartido(partido);
    }

    @Override
    public List<PartidoModel> getPartidosByEtapa(Etapa etapa) {
        List<PartidoModel> listPartidos = new ArrayList<>();

        partidoRepository.findAllByEtapa(etapa).forEach(partido -> listPartidos.add(modelMapper.map(partido, PartidoModel.class)));

        return listPartidos;
    }
}

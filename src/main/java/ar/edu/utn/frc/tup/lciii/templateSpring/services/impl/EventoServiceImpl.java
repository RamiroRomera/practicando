package ar.edu.utn.frc.tup.lciii.templateSpring.services.impl;


import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.eventos.EventoDto;
import ar.edu.utn.frc.tup.lciii.templateSpring.entities.DummyEntity;
import ar.edu.utn.frc.tup.lciii.templateSpring.entities.EventoEntity;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.DummyModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.EventoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.PartidoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.utils.Etapa;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.utils.Evento;
import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.DummyRepository;
import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.EventosRepository;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.DummyService;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.EventoService;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.PartidoService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventosRepository eventosRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PartidoService partidoService;

    @Override
    public PartidoModel crearAmarilla(Long idPartido, EventoDto eventoDto) {
        return modificarPartido(idPartido, eventoDto, Evento.TARJETA_AMARILLA);
    }

    @Override
    public PartidoModel crearRoja(Long idPartido, EventoDto eventoDto) {
        return modificarPartido(idPartido, eventoDto, Evento.TARJETA_ROJA);
    }

    @Override
    public PartidoModel crearGol(Long idPartido, EventoDto eventoDto) {
        return modificarPartido(idPartido, eventoDto, Evento.GOL);
    }

    @Override
    public PartidoModel crearSale(Long idPartido, EventoDto eventoDto) {
        return modificarPartido(idPartido, eventoDto, Evento.SALE);
    }

    @Override
    public PartidoModel crearEntra(Long idPartido, EventoDto eventoDto) {
        return modificarPartido(idPartido, eventoDto, Evento.ENTRA);
    }

    private PartidoModel modificarPartido(Long idPartido, EventoDto eventoDto, Evento eventoEnum) {
        EventoModel evento = new EventoModel();
        PartidoModel partido = partidoService.getPartido(idPartido);
        evento.setEvento(eventoEnum);
        evento.setJugador(eventoDto.getJugador());
        evento.setEtapa(eventoDto.getEtapa());
        evento.setMinuto(eventoDto.getMinuto());

        if (eventoEnum == Evento.TARJETA_AMARILLA || eventoEnum == Evento.TARJETA_ROJA) {
            partido.setAmonestados(partido.getAmonestados() + 1);
        } else if (eventoEnum == Evento.GOL) {
            if (eventoDto.getEquipoLocal()) {
                partido.setGolesLocales(partido.getGolesLocales() + 1);
            } else {
                partido.setGolesVisitante(partido.getGolesVisitante() + 1);
            }
        }

        evento.setPartido(partido);
        eventosRepository.save(modelMapper.map(evento, EventoEntity.class));
        return partidoService.actualizarPartido(partido);
    }
}

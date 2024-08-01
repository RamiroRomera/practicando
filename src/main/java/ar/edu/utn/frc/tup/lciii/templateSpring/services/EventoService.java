package ar.edu.utn.frc.tup.lciii.templateSpring.services;

import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.eventos.EventoDto;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.EventoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.PartidoModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventoService {
    PartidoModel crearAmarilla(Long idPartido, EventoDto eventoDto);
    PartidoModel crearRoja(Long idPartido, EventoDto eventoDto);
    PartidoModel crearGol(Long idPartido, EventoDto eventoDto);
    PartidoModel crearSale(Long idPartido, EventoDto eventoDto);
    PartidoModel crearEntra(Long idPartido, EventoDto eventoDto);

    List<EventoModel> getEventosByPartido(PartidoModel partido);
}

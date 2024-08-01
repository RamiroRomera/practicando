package ar.edu.utn.frc.tup.lciii.templateSpring.services;

import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.eventos.EventoPostDto;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.EventoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.PartidoModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventoService {
    PartidoModel crearAmarilla(Long idPartido, EventoPostDto eventoDto);
    PartidoModel crearRoja(Long idPartido, EventoPostDto eventoDto);
    PartidoModel crearGol(Long idPartido, EventoPostDto eventoDto);
    PartidoModel crearSale(Long idPartido, EventoPostDto eventoDto);
    PartidoModel crearEntra(Long idPartido, EventoPostDto eventoDto);

    List<EventoModel> getEventosByPartido(PartidoModel partido);

    void randomEventos(Long idPartido);

}

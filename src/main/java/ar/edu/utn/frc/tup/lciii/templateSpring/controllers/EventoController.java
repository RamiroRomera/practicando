package ar.edu.utn.frc.tup.lciii.templateSpring.controllers;

import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.eventos.EventoDto;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.PartidoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.EventoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evento")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @Autowired
    private ModelMapper modelMapper;

    @PutMapping("/{idPartido}/amarilla")
    public ResponseEntity<PartidoModel> CrearAmarilla(@PathVariable Long idPartido, @RequestBody @Valid EventoDto eventoDto) {
        return ResponseEntity.ok(eventoService.crearAmarilla(idPartido, eventoDto));
    }
    @PutMapping("/{idPartido}/roja")
    public ResponseEntity<PartidoModel> CrearRoja(@PathVariable Long idPartido, @RequestBody @Valid EventoDto eventoDto) {
        return ResponseEntity.ok(eventoService.crearRoja(idPartido, eventoDto));
    }
    @PutMapping("/{idPartido}/gol")
    public ResponseEntity<PartidoModel> CrearGol(@PathVariable Long idPartido, @RequestBody @Valid EventoDto eventoDto) {
        return ResponseEntity.ok(eventoService.crearGol(idPartido, eventoDto));
    }
    @PutMapping("/{idPartido}/sale")
    public ResponseEntity<PartidoModel> CrearSale(@PathVariable Long idPartido, @RequestBody @Valid EventoDto eventoDto) {
        return ResponseEntity.ok(eventoService.crearSale(idPartido, eventoDto));
    }
    @PutMapping("/{idPartido}/entra")
    public ResponseEntity<PartidoModel> CrearEntra(@PathVariable Long idPartido, @RequestBody @Valid EventoDto eventoDto) {
        return ResponseEntity.ok(eventoService.crearSale(idPartido, eventoDto));
    }
}

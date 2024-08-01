package ar.edu.utn.frc.tup.lciii.templateSpring.controllers;

import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.partido.InfoPartidoDto;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.PartidoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.PartidoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/partido")
public class PartidoController {
    @Autowired
    private PartidoService partidoService;

    @Autowired
    private ModelMapper modelMapper;

    @PutMapping("/terminado/{idPartido}")
    public ResponseEntity<InfoPartidoDto> terminarPartido(@PathVariable Long idPartido) {
        return ResponseEntity.ok(partidoService.terminarPartido(idPartido));
    }
}

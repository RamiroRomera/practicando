package ar.edu.utn.frc.tup.lciii.templateSpring.controllers;

import ar.edu.utn.frc.tup.lciii.templateSpring.services.EventoService;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/random")
public class RandomController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private EventoService eventoService;

    @PatchMapping("/grupos")
    public ResponseEntity<Boolean> randomGrupos() {
        grupoService.randomGrupos();
        return ResponseEntity.ok(true);
    }


    @PatchMapping("/eventos/{idPartido}")
    public ResponseEntity<Boolean> randomEventos(@PathVariable Long idPartido) {
        eventoService.randomEventos(idPartido);
        return ResponseEntity.ok(true);
    }
}

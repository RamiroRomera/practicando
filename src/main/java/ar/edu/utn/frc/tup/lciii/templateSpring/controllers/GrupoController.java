package ar.edu.utn.frc.tup.lciii.templateSpring.controllers;

import ar.edu.utn.frc.tup.lciii.templateSpring.models.GrupoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.PartidoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.GrupoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
    @Autowired
    private GrupoService gruposService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("")
    public ResponseEntity<List<GrupoModel>> crearGrupos() {
        return ResponseEntity.ok(gruposService.crearGrupos());
    }

    @PostMapping("/crear/partidos")
    public ResponseEntity<List<PartidoModel>> crearPartidos() {
        return ResponseEntity.ok(gruposService.crearPartidos());
    }

}

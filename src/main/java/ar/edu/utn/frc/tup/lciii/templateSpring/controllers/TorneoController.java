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
@RequestMapping("/torneo")
public class TorneoController {
    @Autowired
    private GrupoService grupoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/grupo")
    public ResponseEntity<List<GrupoModel>> crearGrupos() {
        return ResponseEntity.ok(grupoService.crearGrupos());
    }

    @PostMapping("/grupo/crear/partidos")
    public ResponseEntity<List<PartidoModel>> crearPartidos() {
        return ResponseEntity.ok(grupoService.crearPartidos());
    }

    @PostMapping("/grupo/cerrar")
    public ResponseEntity<List<PartidoModel>> cerrarGrupo() {
        return ResponseEntity.ok(grupoService.cerrarGrupos());
    }

    @GetMapping("/{idGrupo}")
    public ResponseEntity<GrupoModel> getGrupoById(@PathVariable Long idGrupo) {
        return ResponseEntity.ok(grupoService.getGrupoById(idGrupo));
    }

    @PostMapping("/cuartos/cerrar")
    public ResponseEntity<List<PartidoModel>> cerrarCuartos() {
        return ResponseEntity.ok(grupoService.cerrarCuartos());
    }

    @PostMapping("/semis/cerrar")
    public ResponseEntity<List<PartidoModel>> cerrarSemis() {
        return ResponseEntity.ok(grupoService.cerrarSemis());
    }

    @PostMapping("/tecer_puesto/cerrar")
    public ResponseEntity<List<PartidoModel>> cerrarTercerPuesto() {
        return ResponseEntity.ok(grupoService.cerrarTercerPuesto());
    }

    @PostMapping("/final/cerrar")
    public ResponseEntity<List<PartidoModel>> cerrarFinal() {
        return ResponseEntity.ok(grupoService.cerrarFinal());
    }
}

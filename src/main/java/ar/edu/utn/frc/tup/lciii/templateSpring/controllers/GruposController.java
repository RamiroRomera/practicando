package ar.edu.utn.frc.tup.lciii.templateSpring.controllers;

import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.DummyDto;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.DummyModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.GruposModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.GruposService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GruposController {
    @Autowired
    private GruposService gruposService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("")
    public ResponseEntity<List<GruposModel>> crearGrupos() {
        return ResponseEntity.ok(gruposService.crearGrupos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DummyDto> updateDummy(@RequestBody DummyDto dummy, @PathVariable Long id) {
        DummyModel dummyToUpdate = modelMapper.map(dummy, DummyModel.class);
        dummyToUpdate.setId(id);
        DummyModel dummyModelReceived = gruposService.updateDummy(dummyToUpdate);
        return ResponseEntity.ok(modelMapper.map(dummyModelReceived, DummyDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDummy(@PathVariable Long id) {
        gruposService.deleteDummy(id);
        return ResponseEntity.ok(true);
    }
}

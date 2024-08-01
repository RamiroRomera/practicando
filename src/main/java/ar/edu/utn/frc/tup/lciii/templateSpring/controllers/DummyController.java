package ar.edu.utn.frc.tup.lciii.templateSpring.controllers;

import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.DummyDto;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.DummyModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.DummyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dummy")
public class DummyController {
    @Autowired
    private DummyService dummyService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity<List<DummyDto>> getAllDummy() {
        List<DummyModel> listDummiesToReturn = dummyService.getAllDummy();
        List<DummyDto> listDummyDtoToReturn = new ArrayList<>();
        listDummiesToReturn.forEach(dummyModel -> listDummyDtoToReturn.add(modelMapper.map(dummyModel, DummyDto.class)));
        return ResponseEntity.ok(listDummyDtoToReturn);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DummyDto> getDummy(@PathVariable Long id) {
        DummyModel dummy = dummyService.getDummy(id);
        return ResponseEntity.ok(modelMapper.map(dummy, DummyDto.class));
    }

    @PostMapping("")
    public ResponseEntity<DummyDto> createDummy(@RequestBody DummyDto dummy) {
        DummyModel dummyModelReceived = dummyService.createDummy(modelMapper.map(dummy, DummyModel.class));
        return ResponseEntity.ok(modelMapper.map(dummyModelReceived, DummyDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DummyDto> updateDummy(@RequestBody DummyDto dummy, @PathVariable Long id) {
        DummyModel dummyToUpdate = modelMapper.map(dummy, DummyModel.class);
        dummyToUpdate.setId(id);
        DummyModel dummyModelReceived = dummyService.updateDummy(dummyToUpdate);
        return ResponseEntity.ok(modelMapper.map(dummyModelReceived, DummyDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDummy(@PathVariable Long id) {
        dummyService.deleteDummy(id);
        return ResponseEntity.ok(true);
    }
}

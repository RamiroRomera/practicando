package ar.edu.utn.frc.tup.lciii.templateSpring.services.impl;


import ar.edu.utn.frc.tup.lciii.templateSpring.entities.DummyEntity;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.DummyModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.DummyRepository;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.DummyService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DummyServiceImpl implements DummyService {

    @Autowired
    private DummyRepository dummyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DummyModel> getAllDummy() {
        List<DummyModel> listDummiesToReturn = new ArrayList<>();
        dummyRepository.findAll().forEach(sensorEntity -> listDummiesToReturn.add(modelMapper.map(sensorEntity, DummyModel.class)));
        return listDummiesToReturn;
    }

    @Override
    public DummyModel getDummy(Long id) {
        return modelMapper.map(dummyRepository.getReferenceById(id), DummyModel.class);
    }

    @Override
    public DummyModel createDummy(DummyModel dummy) {
        DummyEntity dummySaved = dummyRepository.save(modelMapper.map(dummy, DummyEntity.class));
        return modelMapper.map(dummySaved, DummyModel.class);
    }

    @Override
    public DummyModel updateDummy(DummyModel dummy) {
        DummyEntity dummySaved = dummyRepository.save(modelMapper.map(dummy, DummyEntity.class));
        return modelMapper.map(dummySaved, DummyModel.class);
    }

    @Override
    public void deleteDummy(Long id) {
        try {
            dummyRepository.deleteById(id);
        } catch (Exception ex) {
            throw new EntityNotFoundException("There is no id on the parameter.");
        }
    }
}

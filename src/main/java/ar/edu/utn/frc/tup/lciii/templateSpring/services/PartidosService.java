package ar.edu.utn.frc.tup.lciii.templateSpring.services;

import ar.edu.utn.frc.tup.lciii.templateSpring.models.DummyModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PartidosService {
    List<DummyModel> getAllDummy();
    DummyModel getDummy(Long id);
    DummyModel createDummy(DummyModel dummy);
    DummyModel updateDummy(DummyModel dummy);
    void deleteDummy(Long id);
}

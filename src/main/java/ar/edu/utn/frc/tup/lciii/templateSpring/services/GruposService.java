package ar.edu.utn.frc.tup.lciii.templateSpring.services;

import ar.edu.utn.frc.tup.lciii.templateSpring.models.DummyModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.GruposModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GruposService {

    List<GruposModel> crearGrupos();
}

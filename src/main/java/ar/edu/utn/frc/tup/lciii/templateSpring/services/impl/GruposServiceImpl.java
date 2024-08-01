package ar.edu.utn.frc.tup.lciii.templateSpring.services.impl;


import ar.edu.utn.frc.tup.lciii.templateSpring.entities.DummyEntity;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.DummyModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.GruposModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.GruposRepository;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.DummyService;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.GruposService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GruposServiceImpl implements GruposService {

    @Autowired
    private GruposRepository gruposRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<GruposModel> crearGrupos() {
        return null;
    }
}

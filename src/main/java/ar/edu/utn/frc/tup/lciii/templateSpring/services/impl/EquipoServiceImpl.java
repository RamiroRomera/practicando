package ar.edu.utn.frc.tup.lciii.templateSpring.services.impl;


import ar.edu.utn.frc.tup.lciii.templateSpring.entities.EquipoEntity;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.EquipoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.EquiposRepository;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.EquipoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private EquiposRepository equiposRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EquipoModel> getAllEquipos() {
        List<EquipoModel> listEquipos = new ArrayList<>();
        equiposRepository.findAll().forEach(
                equiposEntity -> {
                    listEquipos.add(modelMapper.map(equiposEntity, EquipoModel.class));
                }
        );
        return listEquipos;
    }

    @Override
    public EquipoModel getEquipo(Long id) {
        return null;
    }

    @Override
    public EquipoModel actualizarEquipo(EquipoModel equipo) {
        return modelMapper.map(equiposRepository.save(modelMapper.map(equipo, EquipoEntity.class)), EquipoModel.class);
    }
}

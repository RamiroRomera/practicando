package ar.edu.utn.frc.tup.lciii.templateSpring.services.impl;


import ar.edu.utn.frc.tup.lciii.templateSpring.entities.GrupoEntity;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.EquipoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.GrupoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.PartidoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.utils.Etapa;
import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.GruposRepository;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.EquipoService;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.GrupoService;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.PartidoService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GrupoServiceImpl implements GrupoService {

    @Autowired
    private GruposRepository gruposRepository;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private PartidoService partidoService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<GrupoModel> crearGrupos() {
        List<EquipoModel> listEquipos = equipoService.getAllEquipos();

        Collections.shuffle(listEquipos);

        GrupoModel grupoModel;
        List<GrupoModel> listToReturn = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            grupoModel = new GrupoModel();
            grupoModel.setEquipos1(listEquipos.get(0));
            grupoModel.setEquipos2(listEquipos.get(1));
            grupoModel.setEquipos3(listEquipos.get(2));
            grupoModel.setEquipos4(listEquipos.get(3));
            grupoModel.setEquipos5(listEquipos.get(4));

            for (int j = 0; j < 5; j++) {
                listEquipos.remove(0);
            }

            grupoModel.setNombre("Grupo N " + (i + 1));

            GrupoEntity grupoGuardado = gruposRepository.save(modelMapper.map(grupoModel, GrupoEntity.class));

            listToReturn.add(modelMapper.map(grupoGuardado, GrupoModel.class));
        }

        return listToReturn;
    }

    @Override
    public List<PartidoModel> crearPartidos() {
        List<GrupoModel> listGrupos = new ArrayList<>();

        gruposRepository.findAll().forEach(gruposEntity ->
            listGrupos.add(modelMapper.map(gruposEntity, GrupoModel.class))
        );

        if (listGrupos.size() == 0) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "No hay grupos");
        }

        List<PartidoModel> listPartidos = new ArrayList<>();
        List<EquipoModel> listEquipos = new ArrayList<>();
        for (GrupoModel grupo : listGrupos) {
            listEquipos.clear();
            listEquipos.add(grupo.getEquipos1());
            listEquipos.add(grupo.getEquipos2());
            listEquipos.add(grupo.getEquipos3());
            listEquipos.add(grupo.getEquipos4());
            listEquipos.add(grupo.getEquipos5());

            for (int i = 0; i < 4; i++) {
                for (int j = i+1; j < 5; j++) {
                    listPartidos.add(partidoService.crearPartido(listEquipos.get(i), listEquipos.get(j), Etapa.GRUPOS));
                }
            }
        }

        return listPartidos;
    }
}

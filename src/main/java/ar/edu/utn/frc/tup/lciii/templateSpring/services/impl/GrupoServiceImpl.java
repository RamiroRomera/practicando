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

import java.util.*;

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

            for (int j = 0; j < 4; j++) {
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

            for (int i = 0; i < 3; i++) {
                for (int j = i+1; j < 4; j++) {
                    listPartidos.add(partidoService.crearPartido(listEquipos.get(i), listEquipos.get(j), Etapa.GRUPOS));
                }
            }
        }
        return listPartidos;
    }

    @Override
    public List<PartidoModel> cerrarGrupos() {
        List<PartidoModel> listPartidosToReturn = new ArrayList<>();
        List<GrupoModel> listGrupos = new ArrayList<>();
        gruposRepository.findAll().forEach(grupoEntity -> listGrupos.add(modelMapper.map(grupoEntity, GrupoModel.class)));
        int llavesPrimeraLlave = 0;
        int llavesSegundaLlave = 0;
        for (GrupoModel grupo : listGrupos) {
            List<PartidoModel> listPartidos = partidoService.getAllPartidoByGroup(grupo);

            for (PartidoModel partidoModel : listPartidos) {
                if (partidoModel.getTerminado() == false) {
                    throw new HttpClientErrorException(HttpStatusCode.valueOf(404), "No se han terminado todos los partidos");
                }
            }

            List<EquipoModel> listEquipos = new ArrayList<>();
            listEquipos.add(grupo.getEquipos1());
            listEquipos.add(grupo.getEquipos2());
            listEquipos.add(grupo.getEquipos3());
            listEquipos.add(grupo.getEquipos4());

            listEquipos.sort(Comparator.comparing(EquipoModel::getPuntos));
            Collections.reverse(listEquipos);
            if (llavesPrimeraLlave == llavesSegundaLlave) {
                partidoService.crearPartido(listEquipos.get(0), ++llavesPrimeraLlave, Etapa.CUARTOS);
                partidoService.crearPartido(listEquipos.get(1), ++llavesPrimeraLlave, Etapa.CUARTOS);
            } else if (llavesPrimeraLlave > llavesSegundaLlave) {
                listPartidosToReturn.add(partidoService.crearPartido(listEquipos.get(1), ++llavesSegundaLlave, Etapa.CUARTOS));
                listPartidosToReturn.add(partidoService.crearPartido(listEquipos.get(0), ++llavesSegundaLlave, Etapa.CUARTOS));
            }

            listEquipos.get(2).setParticipando(false);
            listEquipos.get(3).setParticipando(false);
            equipoService.actualizarEquipo(listEquipos.get(2));
            equipoService.actualizarEquipo(listEquipos.get(3));
        }
        return listPartidosToReturn;
    }

    @Override
    public void randomGrupos() {
        Random random = new Random();
        List<PartidoModel> listPartidos = new ArrayList<>();
        listPartidos.addAll(partidoService.getAllPartidoByGroup(modelMapper.map(gruposRepository.findById(1L), GrupoModel.class)));
        listPartidos.addAll(partidoService.getAllPartidoByGroup(modelMapper.map(gruposRepository.findById(2L), GrupoModel.class)));
        listPartidos.addAll(partidoService.getAllPartidoByGroup(modelMapper.map(gruposRepository.findById(3L), GrupoModel.class)));
        listPartidos.addAll(partidoService.getAllPartidoByGroup(modelMapper.map(gruposRepository.findById(4L), GrupoModel.class)));

        listPartidos.forEach(partido -> {
            partido.setGolesLocales(random.nextInt(10));
            partido.setGolesLocales(random.nextInt(10));
            partidoService.actualizarPartido(partido);
            partidoService.terminarPartido(partido.getId());
        });
    }

    @Override
    public GrupoModel getGrupoById(Long idGrupo) {
        return modelMapper.map(gruposRepository.findById(idGrupo), GrupoModel.class);
    }

    @Override
    public List<PartidoModel> cerrarCuartos() {
        List<PartidoModel> listPartidosCuartos = partidoService.getPartidosByEtapa(Etapa.CUARTOS);
        List<PartidoModel> listPartidosToReturn = new ArrayList<>();

        int llavesPrimeraLlave = 0;
        int llavesSegundaLlave = 0;
        for (PartidoModel partido : listPartidosCuartos) {
            if (partido.getGolesLocales() > partido.getGolesVisitante()) {
                partido.getEquipoVisitante().setParticipando(false);
                equipoService.actualizarEquipo(partido.getEquipoVisitante());

                if (llavesPrimeraLlave == llavesSegundaLlave) {
                    partidoService.crearPartido(partido.getEquipoLocal(), ++llavesPrimeraLlave, Etapa.SEMI);
                } else if (llavesPrimeraLlave > llavesSegundaLlave) {
                    listPartidosToReturn.add(partidoService.crearPartido(partido.getEquipoLocal(), ++llavesSegundaLlave, Etapa.SEMI));
                }
            } else {
                partido.getEquipoLocal().setParticipando(false);
                equipoService.actualizarEquipo(partido.getEquipoLocal());

                if (llavesPrimeraLlave == llavesSegundaLlave) {
                    partidoService.crearPartido(partido.getEquipoVisitante(), ++llavesPrimeraLlave, Etapa.SEMI);
                } else if (llavesPrimeraLlave > llavesSegundaLlave) {
                    listPartidosToReturn.add(partidoService.crearPartido(partido.getEquipoVisitante(), ++llavesSegundaLlave, Etapa.SEMI));
                }
            }
        }
        return listPartidosToReturn;
    }

    @Override
    public List<PartidoModel> cerrarSemis() {
        List<PartidoModel> listPartidosSemis = partidoService.getPartidosByEtapa(Etapa.SEMI);
        List<PartidoModel> listPartidosToReturn = new ArrayList<>();

        for (PartidoModel partido : listPartidosSemis) {
            if (partido.getGolesLocales() > partido.getGolesVisitante()) {
                partido.getEquipoVisitante().setParticipando(false);
                equipoService.actualizarEquipo(partido.getEquipoVisitante());

                partidoService.crearPartido(partido.getEquipoLocal(), 1, Etapa.FINAL);
                partidoService.crearPartido(partido.getEquipoVisitante(), 1, Etapa.TERCER_PUESTO);
            } else {
                partido.getEquipoLocal().setParticipando(false);
                equipoService.actualizarEquipo(partido.getEquipoLocal());

                partidoService.crearPartido(partido.getEquipoVisitante(), 1, Etapa.FINAL);
                partidoService.crearPartido(partido.getEquipoLocal(), 1, Etapa.TERCER_PUESTO);
            }
        }
        return listPartidosToReturn;
    }

    @Override
    public List<PartidoModel> cerrarTercerPuesto() {
        List<PartidoModel> listPartidosTercerPuesto = partidoService.getPartidosByEtapa(Etapa.TERCER_PUESTO);
        List<PartidoModel> listPartidosToReturn = new ArrayList<>();

        if (listPartidosTercerPuesto.get(0).getGolesLocales() > listPartidosTercerPuesto.get(0).getGolesVisitante()) {
            listPartidosTercerPuesto.get(0).getEquipoVisitante().setParticipando(false);
            equipoService.actualizarEquipo(listPartidosTercerPuesto.get(0).getEquipoVisitante());
        } else {
            listPartidosTercerPuesto.get(0).getEquipoLocal().setParticipando(false);
            equipoService.actualizarEquipo(listPartidosTercerPuesto.get(0).getEquipoLocal());
        }
        return null;
    }

    @Override
    public List<PartidoModel> cerrarFinal() {
        List<PartidoModel> listPartidosFinal = partidoService.getPartidosByEtapa(Etapa.FINAL);
        List<PartidoModel> listPartidosToReturn = new ArrayList<>();

        if (listPartidosFinal.get(0).getGolesLocales() > listPartidosFinal.get(0).getGolesVisitante()) {
            listPartidosFinal.get(0).getEquipoVisitante().setParticipando(false);
            equipoService.actualizarEquipo(listPartidosFinal.get(0).getEquipoVisitante());
        } else {
            listPartidosFinal.get(0).getEquipoLocal().setParticipando(false);
            equipoService.actualizarEquipo(listPartidosFinal.get(0).getEquipoLocal());
        }
        return null;
    }


}

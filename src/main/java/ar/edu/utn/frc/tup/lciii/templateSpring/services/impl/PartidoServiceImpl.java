package ar.edu.utn.frc.tup.lciii.templateSpring.services.impl;


import ar.edu.utn.frc.tup.lciii.templateSpring.entities.PartidoEntity;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.EquipoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.PartidoModel;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.utils.Etapa;
import ar.edu.utn.frc.tup.lciii.templateSpring.repositories.PartidosRepository;
import ar.edu.utn.frc.tup.lciii.templateSpring.services.PartidoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidoServiceImpl implements PartidoService {

    @Autowired
    private PartidosRepository partidoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PartidoModel getPartido(Long id) {
        return null;
    }

    @Override
    public PartidoModel crearPartido(EquipoModel equipoLocal, EquipoModel equipoVisitante, Etapa etapa) {
        PartidoModel partido = new PartidoModel();
        partido.setEquipoLocal(equipoLocal);
        partido.setEquipoVisitante(equipoVisitante);
        partido.setGolesLocales(0);
        partido.setGolesVisitante(0);
        partido.setTerminado(false);
        partido.setEtapa(etapa);
        partido.setAmonestados(0);
        return modelMapper.map(partidoRepository.save(modelMapper.map(partido, PartidoEntity.class)), PartidoModel.class);
    }

    @Override
    public PartidoModel actualizarPartido(PartidoModel partido) {
        return modelMapper.map(partidoRepository.save(modelMapper.map(partido, PartidoEntity.class)), PartidoModel.class);
    }
}

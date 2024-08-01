package ar.edu.utn.frc.tup.lciii.templateSpring.repositories;

import ar.edu.utn.frc.tup.lciii.templateSpring.entities.EquipoEntity;
import ar.edu.utn.frc.tup.lciii.templateSpring.entities.PartidoEntity;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.utils.Etapa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartidosRepository extends JpaRepository<PartidoEntity, Long> {
    List<PartidoEntity> findAllByEquipoLocal(EquipoEntity equipo);
    Optional<PartidoEntity> findByLlaveAndEtapa(Integer llave, Etapa etapa);
}

package ar.edu.utn.frc.tup.lciii.templateSpring.repositories;

import ar.edu.utn.frc.tup.lciii.templateSpring.entities.EventoEntity;
import ar.edu.utn.frc.tup.lciii.templateSpring.entities.PartidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventosRepository extends JpaRepository<EventoEntity, Long> {
    List<EventoEntity> findAllByPartido(PartidoEntity partido);
}

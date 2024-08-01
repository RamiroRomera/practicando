package ar.edu.utn.frc.tup.lciii.templateSpring.repositories;

import ar.edu.utn.frc.tup.lciii.templateSpring.entities.EventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosRepository extends JpaRepository<EventoEntity, Long> {
}

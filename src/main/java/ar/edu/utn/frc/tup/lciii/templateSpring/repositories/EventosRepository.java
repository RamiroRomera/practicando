package ar.edu.utn.frc.tup.lciii.templateSpring.repositories;


import ar.edu.utn.frc.tup.lciii.templateSpring.entities.EventosEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EventosRepository extends JpaRepository<EventosEntity, Long> {
}

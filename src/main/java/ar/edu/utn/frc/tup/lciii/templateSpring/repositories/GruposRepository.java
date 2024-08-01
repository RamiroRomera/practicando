package ar.edu.utn.frc.tup.lciii.templateSpring.repositories;

import ar.edu.utn.frc.tup.lciii.templateSpring.entities.GruposEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GruposRepository extends JpaRepository<GruposEntity, Long> {

}

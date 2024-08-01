package ar.edu.utn.frc.tup.lciii.templateSpring.repositories;

import ar.edu.utn.frc.tup.lciii.templateSpring.entities.EquipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquiposRepository extends JpaRepository<EquipoEntity, Long> {
}

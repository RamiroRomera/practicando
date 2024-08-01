package ar.edu.utn.frc.tup.lciii.templateSpring.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "equipos")
@Data
@Setter
@NoArgsConstructor
public class EquipoEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Boolean participando;
    private Integer puntos;
}

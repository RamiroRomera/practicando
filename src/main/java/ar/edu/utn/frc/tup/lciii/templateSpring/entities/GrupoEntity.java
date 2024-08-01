package ar.edu.utn.frc.tup.lciii.templateSpring.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "grupos")
@Data
@Setter
@NoArgsConstructor
public class GrupoEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToOne
    private EquipoEntity equipos1;
    @ManyToOne
    private EquipoEntity equipos2;
    @ManyToOne
    private EquipoEntity equipos3;
    @ManyToOne
    private EquipoEntity equipos4;
    @ManyToOne
    private EquipoEntity equipos5;
}

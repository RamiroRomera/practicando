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
public class GruposEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private EquiposEntity equipos1;
    @ManyToOne
    private EquiposEntity equipos2;
    @ManyToOne
    private EquiposEntity equipos3;
    @ManyToOne
    private EquiposEntity equipos4;
    @ManyToOne
    private EquiposEntity equipos5;
}

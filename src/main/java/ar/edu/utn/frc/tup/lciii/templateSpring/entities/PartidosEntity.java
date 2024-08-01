package ar.edu.utn.frc.tup.lciii.templateSpring.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "partidos")
@Data
@Setter
@NoArgsConstructor
public class PartidosEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private EquiposEntity equipoVisitante;
    @ManyToOne
    private EquiposEntity equipoLocal;
    private Integer golesVisitante;
    private Integer golesLocales;
    private Integer amonestados;
    private Integer extension;
}

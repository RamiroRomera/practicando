package ar.edu.utn.frc.tup.lciii.templateSpring.entities;

import ar.edu.utn.frc.tup.lciii.templateSpring.models.utils.Etapa;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "partidos")
@Data
@Setter
@NoArgsConstructor
public class PartidoEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private EquipoEntity equipoVisitante;
    @ManyToOne
    private EquipoEntity equipoLocal;
    private Integer golesVisitante;
    private Integer golesLocales;
    private Integer amonestados;
    @Enumerated(value = EnumType.STRING)
    private Etapa etapa;
    private Boolean terminado;
}

package ar.edu.utn.frc.tup.lciii.templateSpring.entities;

import ar.edu.utn.frc.tup.lciii.templateSpring.models.utils.Evento;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "eventos")
@Data
@Setter
@NoArgsConstructor
public class EventoEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Evento evento;
    private String etapa;
    private Integer minuto;
    private String jugador;
    @ManyToOne
    private PartidoEntity partido;
}

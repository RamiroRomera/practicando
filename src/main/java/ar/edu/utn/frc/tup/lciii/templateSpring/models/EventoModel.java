package ar.edu.utn.frc.tup.lciii.templateSpring.models;

import ar.edu.utn.frc.tup.lciii.templateSpring.models.utils.Evento;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
public class EventoModel {
    private Long id;
    private Evento evento;
    private String etapa;
    private Integer minuto;
    private String jugador;
    private PartidoModel partido;
}

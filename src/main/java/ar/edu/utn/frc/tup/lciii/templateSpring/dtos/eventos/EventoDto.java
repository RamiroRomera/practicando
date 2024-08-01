package ar.edu.utn.frc.tup.lciii.templateSpring.dtos.eventos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
public class EventoDto {
    private String etapa;
    private Integer minuto;
    private String jugador;
    private Boolean equipoLocal;
}

package ar.edu.utn.frc.tup.lciii.templateSpring.dtos.eventos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventoPostDto {
    private String etapa;
    private Integer minuto;
    private String jugador;
    private Boolean equipoLocal;
}

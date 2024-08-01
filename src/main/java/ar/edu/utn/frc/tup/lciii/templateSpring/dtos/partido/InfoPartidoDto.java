package ar.edu.utn.frc.tup.lciii.templateSpring.dtos.partido;

import ar.edu.utn.frc.tup.lciii.templateSpring.dtos.eventos.EventoDto;
import ar.edu.utn.frc.tup.lciii.templateSpring.models.EventoModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@NoArgsConstructor
public class InfoPartidoDto {
    private String equipoVisitante;
    private String equipoLocal;
    private Integer golesVisitante;
    private Integer golesLocales;
    private Integer amonestados;
    private String terminado;
    private List<EventoDto> listEventos;
}

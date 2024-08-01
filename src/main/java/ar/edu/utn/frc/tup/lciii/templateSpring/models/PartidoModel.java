package ar.edu.utn.frc.tup.lciii.templateSpring.models;

import ar.edu.utn.frc.tup.lciii.templateSpring.models.utils.Etapa;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
public class PartidoModel {
    private Long id;
    private EquipoModel equipoVisitante;
    private EquipoModel equipoLocal;
    private Integer golesVisitante;
    private Integer golesLocales;
    private Integer amonestados;
    private Etapa etapa;
    private Boolean terminado;
}

package ar.edu.utn.frc.tup.lciii.templateSpring.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
public class PartidosModel {
    private Long id;

    private EquiposModel equipoVisitante;
    private EquiposModel equipoLocal;
    private Integer golesVisitante;
    private Integer golesLocales;
    private Integer amonestados;
    private Integer extension;

}

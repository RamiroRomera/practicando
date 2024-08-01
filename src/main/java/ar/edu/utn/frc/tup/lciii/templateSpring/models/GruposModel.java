package ar.edu.utn.frc.tup.lciii.templateSpring.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
public class GruposModel {
    private Long id;

    private EquiposModel equipos1;
    private EquiposModel equipos2;
    private EquiposModel equipos3;
    private EquiposModel equipos4;
    private EquiposModel equipos5;

}

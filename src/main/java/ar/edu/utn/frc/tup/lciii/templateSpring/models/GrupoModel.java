package ar.edu.utn.frc.tup.lciii.templateSpring.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
public class GrupoModel {
    private Long id;
    private String nombre;
    private EquipoModel equipos1;
    private EquipoModel equipos2;
    private EquipoModel equipos3;
    private EquipoModel equipos4;
    private EquipoModel equipos5;
}

package ar.edu.utn.frc.tup.lciii.templateSpring.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
public class EquipoModel {
    private Long id;
    private String nombre;
    private Boolean participando;
}

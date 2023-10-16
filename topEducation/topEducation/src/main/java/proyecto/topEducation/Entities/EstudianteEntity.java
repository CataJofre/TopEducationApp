package proyecto.topEducation.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "estudiante")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class EstudianteEntity {
    @Id
    @Column(unique = true, nullable = false)
    private Long rut_estudiante;
    private String nombres;
    private String apellidos;
    private LocalDate fecha_nacimiento;
    private int egreso_colegio;
    private String nombre_colegio;
    private String tipo_colegio;
    private double promedio;

    public EstudianteEntity(String rutEstudiante) {
        this.rut_estudiante = Long.parseLong(rutEstudiante);
    }

}

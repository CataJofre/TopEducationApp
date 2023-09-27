package proyecto.topEducation.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="estudiante")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class EstudianteEntity {
    @Id
    @Column(unique = true, nullable = false)
    private Long rut_estudiante;

    private String nombres;
    private String apellidos;
    private Date fecha_nacimiento;
    private int egreso_colegio;
}

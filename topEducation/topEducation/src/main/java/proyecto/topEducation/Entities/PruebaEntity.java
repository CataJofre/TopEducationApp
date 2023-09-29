package proyecto.topEducation.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name="prueba")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PruebaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id_prueba;

    @ManyToOne
    @JoinColumn(name ="rut_estudiante")
    private EstudianteEntity rut_estudiante;

    private LocalDate fecha_examen;
    private int puntaje_obtenido;
}

package proyecto.topEducation.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="arancel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArancelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id_arancel;

    @ManyToOne
    @JoinColumn(name ="rut_estudiante")
    private EstudianteEntity rut_estudiante;
    private int dctos_iniciales;
    private int dcto_tipo_pago;
    private int dcto_tiempo_egreso;
    private int dcto_colegio_procedencia;
    private String tipo_de_pago;
    private int monto_pagar;

}

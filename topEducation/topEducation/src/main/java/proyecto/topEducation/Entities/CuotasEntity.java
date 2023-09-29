package proyecto.topEducation.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="cuotas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuotasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id_cuotas;
    @ManyToOne
    @JoinColumn(name ="arancelId")
    private ArancelEntity arancelId;
    private String estado_cuota;
    private LocalDate fecha_pago;
    private int cuotas_totales;
    private int interes_aplicado;
    private int cuotas_pagadas;
    private int valor_de_cuota;
    private int dcto_media_examenes;

}

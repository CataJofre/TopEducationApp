package proyecto.topEducation;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;
import proyecto.topEducation.Entities.ArancelEntity;
import proyecto.topEducation.Entities.CuotasEntity;
import proyecto.topEducation.Entities.EstudianteEntity;
import proyecto.topEducation.Entities.PruebaEntity;
import proyecto.topEducation.Repositories.ArancelRepository;
import proyecto.topEducation.Repositories.CuotasRepository;
import proyecto.topEducation.Repositories.EstudianteRepository;
import proyecto.topEducation.Repositories.PruebaRepository;
import proyecto.topEducation.Services.ArancelService;
import proyecto.topEducation.Services.CuotasService;
import proyecto.topEducation.Services.EstudianteService;
import proyecto.topEducation.Services.PruebaService;

import java.time.LocalDate;
import java.util.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
public class CuotasTest {

    @Mock
    private PruebaRepository pruebaRepository;
    @Mock
    private CuotasRepository cuotasRepository;
    @InjectMocks
    private PruebaService pruebaService;
    @InjectMocks
    private CuotasService cuotasService;
    @Mock
    private ArancelRepository arancelRepository;

    @Test
    public void getAllCuotas() {
        EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut_estudiante(2013301789L);
        estudiante1.setNombres("Marta");
        estudiante1.setApellidos("Sanchez");
        estudiante1.setFecha_nacimiento(LocalDate.of(1999, 11, 26));
        estudiante1.setEgreso_colegio(2017);
        estudiante1.setNombre_colegio("Liceo Tajamar");
        estudiante1.setTipo_colegio("Municipal");
        estudiante1.setPromedio(890);
        ArancelEntity arancel = new ArancelEntity();
        arancel.setId_arancel(1L);
        arancel.setTipo_de_pago("Cuotas");
        arancel.setRut_estudiante(estudiante1);
        arancel.setDcto_tipo_pago(0);
        arancel.setMonto_pagar(1200000);
        arancel.setDcto_media_examenes(0);
        arancel.setDcto_tiempo_egreso(0);
        arancel.setDcto_colegio_procedencia(20);
        arancel.setCantidad_cuotas(3);
        CuotasEntity cuota1 = new CuotasEntity();
        cuota1.setArancelId(arancel);
        cuota1.setRut_estudiante(estudiante1);
        cuota1.setEstadoCuota("Pagada");
        cuota1.setFechaPago(LocalDate.of(2023, 10, 15));
        cuota1.setCuotas_totales(2);
        cuota1.setInteres_aplicado(0);
        cuota1.setCuotas_pagadas(1);
        cuota1.setValor_de_cuota(400000);
        cuota1.setDcto_media_examenes(0);
        CuotasEntity cuota2 = new CuotasEntity();
        cuota2.setArancelId(arancel);
        cuota2.setRut_estudiante(estudiante1);
        cuota2.setEstadoCuota("Pagada");
        cuota2.setFechaPago(LocalDate.of(2023, 11, 15));
        cuota2.setCuotas_pagadas(1);
        cuota2.setInteres_aplicado(0);
        cuota2.setCuotas_pagadas(1);
        cuota2.setValor_de_cuota(400000);
        cuota2.setDcto_media_examenes(0);
        List<CuotasEntity> listaCuotas = Arrays.asList(cuota1, cuota2);
        when(cuotasRepository.findAll()).thenReturn(listaCuotas);
        List<CuotasEntity> resultado = cuotasService.getAllCuotas();
        assertEquals(2, resultado.size());
    }

    @Test
    public void buscarCuotasPorRutEstudiante() {
        EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut_estudiante(2013301789L);
        estudiante1.setNombres("Marta");
        estudiante1.setApellidos("Sanchez");
        estudiante1.setFecha_nacimiento(LocalDate.of(1999, 11, 26));
        estudiante1.setEgreso_colegio(2017);
        estudiante1.setNombre_colegio("Liceo Tajamar");
        estudiante1.setTipo_colegio("Municipal");
        estudiante1.setPromedio(890);
        ArancelEntity arancel = new ArancelEntity();
        arancel.setId_arancel(1L);
        arancel.setTipo_de_pago("Cuotas");
        arancel.setRut_estudiante(estudiante1);
        arancel.setDcto_tipo_pago(0);
        arancel.setMonto_pagar(1200000);
        arancel.setDcto_media_examenes(0);
        arancel.setDcto_tiempo_egreso(0);
        arancel.setDcto_colegio_procedencia(20);
        arancel.setCantidad_cuotas(2);
        CuotasEntity cuota1 = new CuotasEntity();
        cuota1.setArancelId(arancel);
        cuota1.setRut_estudiante(estudiante1);
        cuota1.setEstadoCuota("Pagada");
        cuota1.setFechaPago(LocalDate.of(2023, 10, 15));
        cuota1.setCuotas_totales(2);
        cuota1.setInteres_aplicado(0);
        cuota1.setCuotas_pagadas(1);
        cuota1.setValor_de_cuota(400000);
        cuota1.setDcto_media_examenes(0);
        CuotasEntity cuota2 = new CuotasEntity();
        cuota2.setArancelId(arancel);
        cuota2.setRut_estudiante(estudiante1);
        cuota2.setEstadoCuota("Pagada");
        cuota2.setFechaPago(LocalDate.of(2023, 11, 15));
        cuota2.setCuotas_pagadas(1);
        cuota2.setInteres_aplicado(0);
        cuota2.setCuotas_pagadas(1);
        cuota2.setValor_de_cuota(400000);
        cuota2.setDcto_media_examenes(0);
        List<CuotasEntity> listaCuotas = Arrays.asList(cuota1, cuota2);
        when(cuotasRepository.findByRutEstudiante(estudiante1.getRut_estudiante())).thenReturn(listaCuotas);
        List<CuotasEntity> resultado = cuotasService.buscarCuotasPorRutEstudiante(estudiante1);
        assertEquals(2, resultado.size());
    }

    @Test
    public void generarCuotasParaEstudiante() {
        EstudianteEntity estudiante = new EstudianteEntity();
        estudiante.setRut_estudiante(2013301789L);
        ArancelEntity arancel = new ArancelEntity();
        arancel.setRut_estudiante(estudiante);
        arancel.setCantidad_cuotas(2);
        arancel.setMonto_pagar(1200000);
        Mockito.when(arancelRepository.findByRutEstudiante(estudiante.getRut_estudiante())).thenReturn(arancel);
        cuotasService.generarCuotasParaEstudiante(estudiante);
        ArgumentCaptor<CuotasEntity> cuotasCaptor = ArgumentCaptor.forClass(CuotasEntity.class);
        verify(cuotasRepository, times(2)).save(cuotasCaptor.capture());
        List<CuotasEntity> cuotasGuardadas = cuotasCaptor.getAllValues();
        assertEquals(2, cuotasGuardadas.size());
    }

    @Test
    public void procesarCuotasVencidas() {
        EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut_estudiante(2013301789L);
        estudiante1.setNombres("Marta");
        estudiante1.setApellidos("Sanchez");
        estudiante1.setFecha_nacimiento(LocalDate.of(1999, 11, 26));
        estudiante1.setEgreso_colegio(2017);
        estudiante1.setNombre_colegio("Liceo Tajamar");
        estudiante1.setTipo_colegio("Municipal");
        estudiante1.setPromedio(890);
        ArancelEntity arancel = new ArancelEntity();
        arancel.setId_arancel(1L);
        arancel.setTipo_de_pago("Cuotas");
        arancel.setRut_estudiante(estudiante1);
        arancel.setDcto_tipo_pago(0);
        arancel.setMonto_pagar(1200000);
        arancel.setDcto_media_examenes(0);
        arancel.setDcto_tiempo_egreso(0);
        arancel.setDcto_colegio_procedencia(20);
        arancel.setCantidad_cuotas(3);
        CuotasEntity cuota1 = new CuotasEntity();
        cuota1.setArancelId(arancel);
        cuota1.setRut_estudiante(estudiante1);
        cuota1.setEstadoCuota("Pendiente");
        cuota1.setFechaPago(LocalDate.of(2023, 9, 15));
        cuota1.setCuotas_totales(2);
        cuota1.setInteres_aplicado(0);
        cuota1.setCuotas_pagadas(1);
        cuota1.setValor_de_cuota(400000);
        cuota1.setDcto_media_examenes(0);
        when(cuotasRepository.findByEstadoCuota("Pendiente")).thenReturn(Collections.singletonList(cuota1));
        cuotasService.procesarCuotasVencidas();
        assertEquals("Vencida", cuota1.getEstadoCuota());
    }

    @Test
    public void obtenerInteresPorMesesAtraso() {
        LocalDate fechaPago = LocalDate.of(2023, 9, 15);
        CuotasEntity cuota = new CuotasEntity();
        cuota.setFechaPago(fechaPago);
        int interes = cuotasService.obtenerInteresPorMesesAtraso(cuota.getFechaPago().getMonthValue());
        assertEquals(15, interes);
    }

    @Test
    public void obtenerInteresPorMesesAtraso2() {
        LocalDate fechaPago = LocalDate.of(2023, 10, 15);
        CuotasEntity cuota = new CuotasEntity();
        cuota.setFechaPago(fechaPago);
        int interes = cuotasService.obtenerInteresPorMesesAtraso(3);
        assertEquals(9, interes);
    }

    @Test
    public void obtenerInteresPorMesesAtraso3() {
        LocalDate fechaPago = LocalDate.of(2023, 10, 15);
        CuotasEntity cuota = new CuotasEntity();
        cuota.setFechaPago(fechaPago);
        int interes = cuotasService.obtenerInteresPorMesesAtraso(2);
        assertEquals(6, interes);
    }

    @Test
    public void registrarPago() {
        CuotasEntity cuota = new CuotasEntity();
        cuota.setId_cuotas(1L);  // ID de la cuota
        cuota.setEstadoCuota("Pendiente");
        cuota.setCuotas_pagadas(1);
        when(cuotasRepository.findById(1L)).thenReturn(Optional.of(cuota));
        List<Long> cuotasPagadasIds = Collections.singletonList(1L);
        cuotasService.registrarPago(cuotasPagadasIds);
        assertEquals("Pagada", cuota.getEstadoCuota());
        assertEquals(2, cuota.getCuotas_pagadas());
    }


    @Test
    public void aplicarDescuentosEnCuotasPendientes() {
        EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut_estudiante(2013301789L);
        estudiante1.setNombres("Marta");
        estudiante1.setApellidos("Sanchez");
        estudiante1.setFecha_nacimiento(LocalDate.of(1999, 11, 26));
        estudiante1.setEgreso_colegio(2017);
        estudiante1.setNombre_colegio("Liceo Tajamar");
        estudiante1.setTipo_colegio("Municipal");
        estudiante1.setPromedio(890);
        ArancelEntity arancel = new ArancelEntity();
        arancel.setId_arancel(1L);
        arancel.setTipo_de_pago("Cuotas");
        arancel.setRut_estudiante(estudiante1);
        arancel.setDcto_tipo_pago(0);
        arancel.setMonto_pagar(1200000);
        arancel.setDcto_media_examenes(0);
        arancel.setDcto_tiempo_egreso(0);
        arancel.setDcto_colegio_procedencia(20);
        arancel.setCantidad_cuotas(3);
        CuotasEntity cuota1 = new CuotasEntity();
        cuota1.setArancelId(arancel);
        cuota1.setRut_estudiante(estudiante1);
        cuota1.setEstadoCuota("Pendiente");
        cuota1.setFechaPago(LocalDate.of(2023, 9, 15));
        cuota1.setCuotas_totales(2);
        cuota1.setInteres_aplicado(0);
        cuota1.setCuotas_pagadas(1);
        cuota1.setValor_de_cuota(400000);
        cuota1.setDcto_media_examenes(0);
        List<CuotasEntity> cuotasPendientesSimuladas = new ArrayList<>();
        cuotasPendientesSimuladas.add(cuota1);
        Mockito.when(cuotasRepository.findByEstadoCuota("Pendiente")).thenReturn(cuotasPendientesSimuladas);
        cuotasService.aplicarDescuentosEnCuotasPendientes();
        Mockito.verify(cuotasRepository, times(1)).save(cuota1);
        assertEquals(0, cuota1.getDcto_media_examenes());
        assertEquals(400000, cuota1.getValor_de_cuota());
    }
}








package proyecto.topEducation;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import proyecto.topEducation.Entities.ArancelEntity;
import proyecto.topEducation.Entities.CuotasEntity;
import proyecto.topEducation.Entities.EstudianteEntity;
import proyecto.topEducation.Entities.PruebaEntity;
import proyecto.topEducation.Repositories.ArancelRepository;
import proyecto.topEducation.Repositories.CuotasRepository;
import proyecto.topEducation.Repositories.EstudianteRepository;
import proyecto.topEducation.Repositories.PruebaRepository;
import proyecto.topEducation.Services.CuotasService;
import proyecto.topEducation.Services.EstudianteService;


import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
public class EstudianteTest {

    @Mock
    private EstudianteRepository estudianteRepository;
    @InjectMocks
    private EstudianteService estudianteService;
    @Mock
    private PruebaRepository pruebaRepository;
    @Mock
    private CuotasRepository cuotasRepository;
    @InjectMocks
    private CuotasService cuotasService;
    @Mock
    private ArancelRepository arancelRepository;

    @Test
    public void encontrarEstudiantePorId(){
        EstudianteService estudianteService = Mockito.mock(EstudianteService.class);
        EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut_estudiante(2013301789L);
        estudiante1.setNombres("Marta");
        estudiante1.setApellidos("Sanchez");
        estudiante1.setFecha_nacimiento(LocalDate.of(1999, 11, 26));
        estudiante1.setEgreso_colegio(2017);
        estudiante1.setNombre_colegio("Liceo Tajamar");
        estudiante1.setTipo_colegio("Municipal2");
        estudiante1.setPromedio(890);
        when(estudianteService.findEstudiantePorId(2013301789L)).thenReturn(estudiante1);
        EstudianteEntity estudiante2 = estudianteService.findEstudiantePorId(2013301789L);
        assertEquals(estudiante2.getRut_estudiante(),  estudiante1.getRut_estudiante());
        assertEquals(estudiante2.getNombres(), estudiante1.getNombres());
    }
    @Test
    public void obtenerEstudiantes() {
        // Crear un estudiante de ejemplo
        EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut_estudiante(2013301789L);
        estudiante1.setNombres("Marta");
        estudiante1.setApellidos("Sanchez");
        estudiante1.setFecha_nacimiento(LocalDate.of(1999, 11, 26));
        estudiante1.setEgreso_colegio(2017);
        estudiante1.setNombre_colegio("Liceo Tajamar");
        estudiante1.setTipo_colegio("Municipal2");
        estudiante1.setPromedio(890);
        List<EstudianteEntity> listaEstudiantes = Arrays.asList(estudiante1);
        when(estudianteRepository.findAll()).thenReturn(listaEstudiantes);
        List<EstudianteEntity> resultado = estudianteService.obtenerEstudiantes();
        assertEquals(1, resultado.size());
    }
    @Test
    public void crearEstudiante() {

        EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut_estudiante(2013301789L);
        estudiante1.setNombres("Marta");
        estudiante1.setApellidos("Sanchez");
        estudiante1.setFecha_nacimiento(LocalDate.of(1999, 11, 26));
        estudiante1.setEgreso_colegio(2017);
        estudiante1.setNombre_colegio("Liceo Tajamar");
        estudiante1.setTipo_colegio("Municipal1");
        estudiante1.setPromedio(890);
        Mockito.when(estudianteRepository.save(Mockito.any(EstudianteEntity.class))).thenReturn(estudiante1);

        EstudianteEntity resultado = estudianteService.crearEstudiante(estudiante1);
        assertEquals(resultado.getRut_estudiante(), estudiante1.getRut_estudiante());
    }

    @Test
    public void obtenerPromedioExamen() {
        EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut_estudiante(2013301789L);
        estudiante1.setNombres("Marta");
        estudiante1.setApellidos("Sanchez");
        estudiante1.setFecha_nacimiento(LocalDate.of(1999, 11, 26));
        estudiante1.setEgreso_colegio(2017);
        estudiante1.setNombre_colegio("Liceo Tajamar");
        estudiante1.setTipo_colegio("Municipal");
        estudiante1.setPromedio(890);

        Mockito.when(estudianteRepository.findEstudiantePorId(estudiante1.getRut_estudiante())).thenReturn(estudiante1);

        double promedio = estudianteService.obtenerPromedioPuntajeExamenes(estudiante1.getRut_estudiante());
        assertEquals(890, promedio, 0.0);
    }
    @Test
    public void obtenerPromedioExamen2() {
        EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut_estudiante(2013301789L);
        estudiante1.setNombres("Marta");
        estudiante1.setApellidos("Sanchez");
        estudiante1.setFecha_nacimiento(LocalDate.of(1999, 11, 26));
        estudiante1.setEgreso_colegio(2017);
        estudiante1.setNombre_colegio("Liceo Tajamar");
        estudiante1.setTipo_colegio("Municipal");

        // Configura el comportamiento del repositorio simulado
        Mockito.when(estudianteRepository.findEstudiantePorId(estudiante1.getRut_estudiante())).thenReturn(estudiante1);

        double promedio = estudianteService.obtenerPromedioPuntajeExamenes(estudiante1.getRut_estudiante());
        assertEquals(0.0, promedio);
    }

    @Test
    public void obtenerNombreEstudiante() {
        // Configura el comportamiento del repositorio simulado
        EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut_estudiante(2013301789L);
        estudiante1.setNombres("Marta");
        estudiante1.setApellidos("Sanchez");
        estudiante1.setFecha_nacimiento(LocalDate.of(1999, 11, 26));
        estudiante1.setEgreso_colegio(2017);
        estudiante1.setNombre_colegio("Liceo Tajamar");
        estudiante1.setTipo_colegio("Municipal");
        estudiante1.setPromedio(890);

        Mockito.when(estudianteRepository.findEstudiantePorId(Mockito.anyLong())).thenReturn(estudiante1);

        String nombre = estudianteService.obtenerNombreEstudiante(estudiante1.getRut_estudiante());
        assertEquals("Marta Sanchez", nombre);
    }
    @Test
    public void calcularNroExamenesRendidos() {
        EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut_estudiante(2013301789L);
        estudiante1.setNombres("Marta");
        estudiante1.setApellidos("Sanchez");
        estudiante1.setFecha_nacimiento(LocalDate.of(1999, 11, 26));
        estudiante1.setEgreso_colegio(2017);
        estudiante1.setNombre_colegio("Liceo Tajamar");
        estudiante1.setTipo_colegio("Municipal");
        estudiante1.setPromedio(890);
        Mockito.lenient().when(estudianteRepository.findEstudiantePorId(Mockito.anyLong())).thenReturn(estudiante1);
        PruebaEntity prueba1 = new PruebaEntity();
        prueba1.setFecha_examen(LocalDate.of(2023, 11, 26));
        prueba1.setRut_estudiante(estudiante1);
        prueba1.setId_prueba(3L);
        prueba1.setPuntaje_obtenido(900);
        Mockito.lenient().when(pruebaRepository.countExamenesRendidosByRutEstudiante(Mockito.anyLong())).thenReturn(1);
        int cantidad = estudianteService.calcularNroExamenesRendidos(2013301789L);
        assertEquals(1, cantidad);
    }

    @Test
    public void obtenerFechaUltimoPago(){
      EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut_estudiante(2013301789L);
        estudiante1.setNombres("Marta");
        estudiante1.setApellidos("Sanchez");
        estudiante1.setFecha_nacimiento(LocalDate.of(1999, 11, 26));
        estudiante1.setEgreso_colegio(2017);
        estudiante1.setNombre_colegio("Liceo Tajamar");
        estudiante1.setTipo_colegio("Municipal");
        estudiante1.setPromedio(890);
      ArancelEntity arancel= new ArancelEntity();
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
        cuota1.setEstadoCuota("Pendiente");
        cuota1.setFechaPago(LocalDate.of(2023, 10, 15));
        cuota1.setCuotas_totales(2);
        cuota1.setInteres_aplicado(0);
        cuota1.setCuotas_pagadas(1);
        cuota1.setValor_de_cuota(60000);
        cuota1.setDcto_media_examenes(0);

        CuotasEntity cuota2 = new CuotasEntity();
        cuota2.setArancelId(arancel);
        cuota2.setRut_estudiante(estudiante1);
        cuota2.setEstadoCuota("Pagada");
        cuota2.setFechaPago(LocalDate.of(2023, 11, 15));
        cuota2.setCuotas_pagadas(1);
        cuota2.setInteres_aplicado(0);
        cuota2.setCuotas_pagadas(1);
        cuota2.setValor_de_cuota(60000);
        cuota2.setDcto_media_examenes(0);
      when(cuotasRepository.findMaxFechaPagoByRutEstudianteAndEstadoCuota(2013301789L, "Pagada"))
              .thenReturn(cuota2.getFechaPago());
        LocalDate fecha= estudianteService.obtenerFechaUltimoPago(2013301789L);
        assertEquals(LocalDate.of(2023, 11, 15), fecha);
    }

    @Test
    public void calcularSaldoPorPagar(){
        EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut_estudiante(2013301789L);
        estudiante1.setNombres("Marta");
        estudiante1.setApellidos("Sanchez");
        estudiante1.setFecha_nacimiento(LocalDate.of(1999, 11, 26));
        estudiante1.setEgreso_colegio(2017);
        estudiante1.setNombre_colegio("Liceo Tajamar");
        estudiante1.setTipo_colegio("Municipal");
        estudiante1.setPromedio(890);
        ArancelEntity arancel= new ArancelEntity();
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
        CuotasEntity cuota3 = new CuotasEntity();
        cuota3.setArancelId(arancel);
        cuota3.setRut_estudiante(estudiante1);
        cuota3.setEstadoCuota("Vencida");
        cuota3.setFechaPago(LocalDate.of(2023, 11, 15));
        cuota3.setCuotas_pagadas(1);
        cuota3.setInteres_aplicado(0);
        cuota3.setCuotas_pagadas(1);
        cuota3.setValor_de_cuota(400000);
        cuota3.setDcto_media_examenes(0);
        when(arancelRepository.findByRutEstudiante(2013301789L)).thenReturn(arancel);
        when(cuotasRepository.sumSaldoPorPagar(2013301789L)).thenReturn(800000);

        int saldo = estudianteService.calcularSaldoPorPagar(2013301789L);
        assertEquals(800000, saldo);
    }


    @Test
    public void calcularSaldoPorPagar2() {
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
        arancel.setTipo_de_pago("Contado");
        arancel.setRut_estudiante(estudiante1);
        arancel.setDcto_tipo_pago(0);
        arancel.setMonto_pagar(1200000);
        arancel.setDcto_media_examenes(0);
        arancel.setDcto_tiempo_egreso(0);
        arancel.setDcto_colegio_procedencia(20);
        arancel.setCantidad_cuotas(3);
        when(arancelRepository.findByRutEstudiante(2013301789L)).thenReturn(arancel);
        int saldo = estudianteService.calcularSaldoPorPagar(2013301789L);
        assertEquals(0, saldo);
    }

    @Test
    public void calcularNroCuotasRetraso(){
        EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut_estudiante(2013301789L);
        estudiante1.setNombres("Marta");
        estudiante1.setApellidos("Sanchez");
        estudiante1.setFecha_nacimiento(LocalDate.of(1999, 11, 26));
        estudiante1.setEgreso_colegio(2017);
        estudiante1.setNombre_colegio("Liceo Tajamar");
        estudiante1.setTipo_colegio("Municipal");
        estudiante1.setPromedio(890);
        ArancelEntity arancel= new ArancelEntity();
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
        CuotasEntity cuota3 = new CuotasEntity();
        cuota3.setArancelId(arancel);
        cuota3.setRut_estudiante(estudiante1);
        cuota3.setEstadoCuota("Vencida");
        cuota3.setFechaPago(LocalDate.of(2023, 11, 15));
        cuota3.setCuotas_pagadas(1);
        cuota3.setInteres_aplicado(0);
        cuota3.setCuotas_pagadas(1);
        cuota3.setValor_de_cuota(400000);
        cuota3.setDcto_media_examenes(0);
        when(estudianteService.calcularNroCuotasRetraso(2013301789L)).thenReturn(1);
        int cuotas = estudianteService.calcularNroCuotasRetraso(2013301789L);
        assertEquals(1, cuotas);
    }

    @Test
    public void calcularNroCuotasPagadas(){
        EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut_estudiante(2013301789L);
        estudiante1.setNombres("Marta");
        estudiante1.setApellidos("Sanchez");
        estudiante1.setFecha_nacimiento(LocalDate.of(1999, 11, 26));
        estudiante1.setEgreso_colegio(2017);
        estudiante1.setNombre_colegio("Liceo Tajamar");
        estudiante1.setTipo_colegio("Municipal");
        estudiante1.setPromedio(890);
        ArancelEntity arancel= new ArancelEntity();
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
        CuotasEntity cuota3 = new CuotasEntity();
        cuota3.setArancelId(arancel);
        cuota3.setRut_estudiante(estudiante1);
        cuota3.setEstadoCuota("Vencida");
        cuota3.setFechaPago(LocalDate.of(2023, 11, 15));
        cuota3.setCuotas_pagadas(1);
        cuota3.setInteres_aplicado(0);
        cuota3.setCuotas_pagadas(1);
        cuota3.setValor_de_cuota(400000);
        cuota3.setDcto_media_examenes(0);
        when(estudianteService.calcularNroCuotasPagadas(2013301789L)).thenReturn(2);
        int cuotas = estudianteService.calcularNroCuotasPagadas(2013301789L);
        assertEquals(2, cuotas);
    }

    @Test
    public void calcularMontoTotalPagado(){
        EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut_estudiante(2013301789L);
        estudiante1.setNombres("Marta");
        estudiante1.setApellidos("Sanchez");
        estudiante1.setFecha_nacimiento(LocalDate.of(1999, 11, 26));
        estudiante1.setEgreso_colegio(2017);
        estudiante1.setNombre_colegio("Liceo Tajamar");
        estudiante1.setTipo_colegio("Municipal");
        estudiante1.setPromedio(890);
        ArancelEntity arancel= new ArancelEntity();
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
        CuotasEntity cuota3 = new CuotasEntity();
        cuota3.setArancelId(arancel);
        cuota3.setRut_estudiante(estudiante1);
        cuota3.setEstadoCuota("Vencida");
        cuota3.setFechaPago(LocalDate.of(2023, 11, 15));
        cuota3.setCuotas_pagadas(1);
        cuota3.setInteres_aplicado(0);
        cuota3.setCuotas_pagadas(1);
        cuota3.setValor_de_cuota(400000);
        cuota3.setDcto_media_examenes(0);
        when(arancelRepository.findByRutEstudiante(2013301789L)).thenReturn(arancel);
        when(estudianteService.calcularMontoTotalPagado(2013301789L)).thenReturn(800000);
        int cuotas = estudianteService.calcularMontoTotalPagado(2013301789L);
        assertEquals(800000, cuotas);
    }
    @Test
    public void calcularMontoTotalPagado2(){  EstudianteEntity estudiante1 = new EstudianteEntity();
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
        arancel.setTipo_de_pago("Contado");
        arancel.setRut_estudiante(estudiante1);
        arancel.setDcto_tipo_pago(0);
        arancel.setMonto_pagar(750000);
        arancel.setDcto_media_examenes(0);
        arancel.setDcto_tiempo_egreso(0);
        arancel.setDcto_colegio_procedencia(20);
        arancel.setCantidad_cuotas(3);
        when(arancelRepository.findByRutEstudiante(2013301789L)).thenReturn(arancel);
        int monto = estudianteService.calcularMontoTotalPagado(2013301789L);
        assertEquals(750000, monto);
    }
    @Test
    public void obtenerTipoPago(){  EstudianteEntity estudiante1 = new EstudianteEntity();
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
        arancel.setMonto_pagar(750000);
        arancel.setDcto_media_examenes(0);
        arancel.setDcto_tiempo_egreso(0);
        arancel.setDcto_colegio_procedencia(20);
        arancel.setCantidad_cuotas(3);
        when(arancelRepository.findByRutEstudiante(2013301789L)).thenReturn(arancel);
       String pago = estudianteService.obtenerTipoPago(2013301789L);
        assertEquals("Cuotas", pago);
    }
    @Test
    public void obtenerNroTotalCuotasPactadas(){  EstudianteEntity estudiante1 = new EstudianteEntity();
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
        arancel.setMonto_pagar(750000);
        arancel.setDcto_media_examenes(0);
        arancel.setDcto_tiempo_egreso(0);
        arancel.setDcto_colegio_procedencia(20);
        arancel.setCantidad_cuotas(3);
        when(arancelRepository.findByRutEstudiante(2013301789L)).thenReturn(arancel);
        int pago = estudianteService.obtenerNroTotalCuotasPactadas(2013301789L);
        assertEquals(3, pago);
    }
    @Test
    public void calcularMontoTotalArancel(){  EstudianteEntity estudiante1 = new EstudianteEntity();
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
        arancel.setMonto_pagar(1500000);
        arancel.setDcto_media_examenes(0);
        arancel.setDcto_tiempo_egreso(0);
        arancel.setDcto_colegio_procedencia(20);
        arancel.setCantidad_cuotas(3);
        when(arancelRepository.findByRutEstudiante(2013301789L)).thenReturn(arancel);
        int pago = estudianteService.calcularMontoTotalArancel(2013301789L);
        assertEquals(1500000, pago);
    }
    @Test
    public void calcularMontoTotalArancel2(){  EstudianteEntity estudiante1 = new EstudianteEntity();
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
        arancel.setTipo_de_pago("Contado");
        arancel.setRut_estudiante(estudiante1);
        arancel.setDcto_tipo_pago(0);
        arancel.setMonto_pagar(750000);
        arancel.setDcto_media_examenes(0);
        arancel.setDcto_tiempo_egreso(0);
        arancel.setDcto_colegio_procedencia(20);
        arancel.setCantidad_cuotas(3);
        when(arancelRepository.findByRutEstudiante(2013301789L)).thenReturn(arancel);
        int pago = estudianteService.calcularMontoTotalArancel(2013301789L);
        assertEquals(750000, pago);
    }

}

package proyecto.topEducation;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import proyecto.topEducation.Entities.ArancelEntity;
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.ExpectedCount.times;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
public class PruebaTest {
    @Mock
    private EstudianteRepository estudianteRepository;
    @InjectMocks
    private ArancelService arancelService;
    @InjectMocks
    private EstudianteService estudianteService;
    @Mock
    private PruebaRepository pruebaRepository;
    @InjectMocks
    private PruebaService pruebaService;
    @Mock
    private CuotasRepository cuotasRepository;
    @InjectMocks
    private CuotasService cuotasService;
    @Mock
    private ArancelRepository arancelRepository;

    @Test
    public void obtenerTodasLasPruebas(){
        PruebaEntity prueba1 = new PruebaEntity();
        EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut_estudiante(2013301789L);
        prueba1.setId_prueba(1L);
        prueba1.setFecha_examen(LocalDate.of(2023, 9, 26));
        prueba1.setRut_estudiante(estudiante1);
        prueba1.setPuntaje_obtenido(900);
        PruebaEntity prueba2 = new PruebaEntity();
        prueba2.setId_prueba(2L);
        prueba2.setFecha_examen(LocalDate.of(2023, 9, 27));
        prueba2.setRut_estudiante(estudiante1);
        prueba2.setPuntaje_obtenido(950);
        when(pruebaRepository.findAll()).thenReturn(Arrays.asList(prueba1, prueba2));
        List<PruebaEntity> pruebas = pruebaService.obtenerTodasLasPruebas();
        assertNotNull(pruebas);
        assertEquals(2, pruebas.size());
    }
    @Test
    public void calcularPromedioYDescuentoPorMes() {
        when(pruebaRepository.encontrarMesMasGrande()).thenReturn(9);
        EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut_estudiante(2013301789L);
        List<PruebaEntity> pruebasMock = Arrays.asList(
                new PruebaEntity(1L, estudiante1 ,LocalDate.of(2023, 9, 26),  900),
                new PruebaEntity(2L, estudiante1, LocalDate.of(2023, 9, 27), 950)
        );
        when(pruebaRepository.obtenerPruebasPorMesMasGrande(9)).thenReturn(pruebasMock);
        ArancelEntity arancelMock = new ArancelEntity();
        arancelMock.setDcto_media_examenes(20);
        when(arancelRepository.findByRutEstudiante(2013301789L)).thenReturn(arancelMock);
        pruebaService.calcularPromedioYDescuentoPorMes();
        verify(pruebaRepository).encontrarMesMasGrande();
        verify(pruebaRepository).obtenerPruebasPorMesMasGrande(9);
        verify(arancelRepository).findByRutEstudiante(2013301789L);
        verify(arancelRepository).save(arancelMock);
        verify(estudianteRepository).save(any(EstudianteEntity.class));
    }

    @Test
    public void testCalcularDescuento() {
        assertEquals(10, pruebaService.calcularDescuento(1000));
        assertEquals(10, pruebaService.calcularDescuento(951));
        assertEquals(5, pruebaService.calcularDescuento(949));
        assertEquals(2, pruebaService.calcularDescuento(850));
        assertEquals(0, pruebaService.calcularDescuento(800));
    }

}

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
import proyecto.topEducation.Entities.ArancelEntity;
import proyecto.topEducation.Entities.EstudianteEntity;
import proyecto.topEducation.Repositories.ArancelRepository;
import proyecto.topEducation.Repositories.CuotasRepository;
import proyecto.topEducation.Repositories.EstudianteRepository;
import proyecto.topEducation.Repositories.PruebaRepository;
import proyecto.topEducation.Services.ArancelService;
import proyecto.topEducation.Services.CuotasService;
import proyecto.topEducation.Services.EstudianteService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
public class ArancelTest {
    @Mock
    private EstudianteRepository estudianteRepository;
    @InjectMocks
    private ArancelService arancelService;
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
    public void obtenerTodosLosAranceles() {
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
        arancel.setMonto_pagar(1500000);
        arancel.setDcto_media_examenes(0);
        arancel.setDcto_tiempo_egreso(0);
        arancel.setDcto_colegio_procedencia(20);
        arancel.setCantidad_cuotas(3);
        List<ArancelEntity> listaAranceles = Arrays.asList(arancel);
        when(arancelRepository.findAll()).thenReturn(listaAranceles);
        List<ArancelEntity> resultado = arancelService.obtenerTodosLosAranceles();
        assertEquals(1, resultado.size());
    }
    @Test
    public void crearArancel(){
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
        arancel.setMonto_pagar(1500000);
        arancel.setDcto_media_examenes(0);
        arancel.setDcto_tiempo_egreso(0);
        arancel.setDcto_colegio_procedencia(20);
        arancel.setCantidad_cuotas(3);
        Mockito.when(arancelRepository.save(Mockito.any(ArancelEntity.class))).thenReturn(arancel);

       ArancelEntity resultado = arancelService.crearArancel(arancel);
        assertEquals(resultado.getMonto_pagar(), resultado.getMonto_pagar());
    }

}

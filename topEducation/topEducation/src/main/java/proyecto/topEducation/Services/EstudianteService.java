package proyecto.topEducation.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.topEducation.Entities.ArancelEntity;
import proyecto.topEducation.Entities.EstudianteEntity;
import proyecto.topEducation.Repositories.ArancelRepository;
import proyecto.topEducation.Repositories.CuotasRepository;
import proyecto.topEducation.Repositories.EstudianteRepository;
import proyecto.topEducation.Repositories.PruebaRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class EstudianteService {
    @Autowired
    EstudianteRepository estudianteRepository;
    @Autowired
    ArancelRepository arancelRepository;
    @Autowired
    ArancelService arancelService;
    @Autowired
    PruebaRepository pruebaRepository;

    @Autowired
    CuotasService cuotasService;
    public EstudianteEntity crearEstudiante(EstudianteEntity estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public List<EstudianteEntity> obtenerEstudiantes() {
        return estudianteRepository.findAll();
    }
    //3
    public EstudianteEntity obtenerEstudiantePorId(Long rut_estudiante) {
        return estudianteRepository.findById(rut_estudiante).orElse(null);
    }

    //1
    public double obtenerPromedioPuntajeExamenes(long rutEstudiante) {
        EstudianteEntity estudiante = estudianteRepository.obtenerEstudiantePorId(rutEstudiante);
        if (estudiante != null) {
            return estudiante.getPromedio();
        } else {
            return 0.0;
        }
    }
    //12
    public String obtenerNombreEstudiante(long rutEstudiante) {
        EstudianteEntity estudiante = estudianteRepository.obtenerEstudiantePorId(rutEstudiante);
        return estudiante.getNombres();
    }

    //7
    public int calcularNroExamenesRendidos(long rutEstudiante) {
        return pruebaRepository.countExamenesRendidosByRutEstudiante(rutEstudiante);
    }

    //4
    public LocalDate obtenerFechaUltimoPago(long estudiante) {
        return cuotasService.obtenerFechaUltimoPago(estudiante);
    }

    //5
    public int calcularSaldoPorPagar(long estudiante) {
        return cuotasService.calcularSaldoPorPagar(estudiante);
    }

    //6
    public int calcularNroCuotasRetraso(long rutEstudiante) {
        return cuotasService.calcularNroCuotasRetraso(rutEstudiante);
    }

    //10
    public int calcularNroCuotasPagadas(long rutEstudiante) {
        return cuotasService.calcularNroCuotasPagadas(rutEstudiante);
    }
    //11
    public int calcularMontoTotalPagado(long estudiante) {
            return cuotasService.calcularMontoTotalPagado(estudiante);
    }

    //2
    public String obtenerTipoPago(long estudiante) {

        return arancelService.obtenerTipoPago(estudiante);
    }

    //8
    public int calcularMontoTotalArancel(long estudiante) {
            return arancelService.calcularMontoTotalArancel(estudiante);
    }

    //9
    public int obtenerNroTotalCuotasPactadas(long estudiante) {
        return arancelService.obtenerNroTotalCuotasPactadas(estudiante);
    }









}

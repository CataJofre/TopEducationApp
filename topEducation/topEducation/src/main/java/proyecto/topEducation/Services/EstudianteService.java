package proyecto.topEducation.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
    CuotasRepository cuotasRepository;
    @Autowired
    CuotasService cuotasService;
    public EstudianteEntity crearEstudiante(EstudianteEntity estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public List<EstudianteEntity> obtenerEstudiantes() {
        return estudianteRepository.findAll();
    }
    //3
    public EstudianteEntity findEstudiantePorId(Long rut_estudiante) {
        return estudianteRepository.findById(rut_estudiante).orElse(null);
    }

    public double obtenerPromedioPuntajeExamenes(Long rutEstudiante) {
        EstudianteEntity estudiante = estudianteRepository.findEstudiantePorId(rutEstudiante);
        if (estudiante != null) {
            return estudiante.getPromedio();
        } else {
            return 0.0;
        }
    }
    //12
    public String obtenerNombreEstudiante(Long rutEstudiante) {
        EstudianteEntity estudiante = estudianteRepository.findEstudiantePorId(rutEstudiante);
        return estudiante.getNombres()+" "+estudiante.getApellidos();
    }

    //7
    public int calcularNroExamenesRendidos(Long rutEstudiante) {
        return pruebaRepository.countExamenesRendidosByRutEstudiante(rutEstudiante);
    }

    //4
    public LocalDate obtenerFechaUltimoPago(Long estudiante) {
        return cuotasRepository.findMaxFechaPagoByRutEstudianteAndEstadoCuota(estudiante, "Pagada");
    }

    //5
    public int calcularSaldoPorPagar(Long estudiante) {
        ArancelEntity arancel = arancelRepository.findByRutEstudiante(estudiante);
        if (arancel.getTipo_de_pago().equals("Contado")) {
            return 0;
        } else {
            Integer saldo = cuotasRepository.sumSaldoPorPagar(estudiante);
            return saldo != null ? saldo : 0;
        }
    }


    //6
    public int calcularNroCuotasRetraso(Long rutEstudiante) {
        return cuotasRepository.countCuotasVencidasByRutEstudiante(rutEstudiante);
    }

    //10
    public int calcularNroCuotasPagadas(Long rutEstudiante) {
        return cuotasRepository.countCuotasPagadasByRutEstudiante(rutEstudiante);
    }
    //11
    public int calcularMontoTotalPagado(Long estudiante) {
        ArancelEntity arancel = arancelRepository.findByRutEstudiante(estudiante);
        if (arancel.getTipo_de_pago().equals("Contado")) {
            return 750000;
        } else {
            return cuotasRepository.sumMontoTotalPagadoByRutEstudiante(estudiante);
        }
    }

    //2
    public String obtenerTipoPago(Long estudiante) {
        ArancelEntity arancel = arancelRepository.findByRutEstudiante(estudiante);
        return arancel.getTipo_de_pago();
    }

    //8
    public int calcularMontoTotalArancel(Long estudiante) {
        ArancelEntity arancel = arancelRepository.findByRutEstudiante(estudiante);
        if(arancel.getTipo_de_pago().equals( "Contado")){
            return 750000 * (1-arancel.getDcto_media_examenes()/100);
        }
        else{
            return arancel.getMonto_pagar() * (1-arancel.getDcto_media_examenes()/100);
        }
    }

    //9
    public int obtenerNroTotalCuotasPactadas(Long estudiante) {
        ArancelEntity arancel = arancelRepository.findByRutEstudiante(estudiante);
        return arancel.getCantidad_cuotas();
    }









}

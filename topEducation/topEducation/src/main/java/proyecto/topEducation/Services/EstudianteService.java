package proyecto.topEducation.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.topEducation.Entities.EstudianteEntity;
import proyecto.topEducation.Repositories.EstudianteRepository;

import java.util.List;

@Service
public class EstudianteService {
    @Autowired
    EstudianteRepository estudianteRepository;

    public EstudianteEntity crearEstudiante(EstudianteEntity estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public EstudianteEntity obtenerEstudiantePorId(Long rut_estudiante) {
        return estudianteRepository.findById(rut_estudiante).orElse(null);
    }
    public List<EstudianteEntity> obtenerEstudiantes() {
        return estudianteRepository.findAll();
    }
    public EstudianteEntity actualizarEstudiante(Long rut_estudiante, EstudianteEntity estudianteActualizado) {
        EstudianteEntity estudianteExistente = estudianteRepository.findById(rut_estudiante).orElse(null);
        if (estudianteExistente != null) {
            estudianteExistente.setNombres(estudianteActualizado.getNombres());
            estudianteExistente.setApellidos(estudianteActualizado.getApellidos());
            estudianteExistente.setFecha_nacimiento(estudianteActualizado.getFecha_nacimiento());
            estudianteExistente.setEgreso_colegio(estudianteActualizado.getEgreso_colegio());
            estudianteExistente.setTipo_colegio(estudianteActualizado.getTipo_colegio());
            estudianteExistente.setNombre_colegio(estudianteActualizado.getNombre_colegio());
            return estudianteRepository.save(estudianteExistente);
        }
        return null;
    }

    public void eliminarEstudiante(Long rut_estudiante) {
        estudianteRepository.deleteById(rut_estudiante);
    }

}

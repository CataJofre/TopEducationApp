package proyecto.topEducation.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.topEducation.Repositories.EstudianteRepository;

@Service
public class EstudianteService {
    @Autowired
    EstudianteRepository estudianteRepository;
}

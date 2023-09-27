package proyecto.topEducation.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.topEducation.Repositories.CuotasRepository;

@Service
public class CuotasService {
    @Autowired
    CuotasRepository cuotasRepository;
}

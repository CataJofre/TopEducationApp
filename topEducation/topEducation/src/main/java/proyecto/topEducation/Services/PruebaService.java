package proyecto.topEducation.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.topEducation.Repositories.PruebaRepository;

@Service
public class PruebaService {
    @Autowired
    PruebaRepository pruebaRepository;
}

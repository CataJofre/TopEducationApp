package proyecto.topEducation.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.topEducation.Repositories.ArancelRepository;

@Service
public class ArancelService {
    @Autowired
    ArancelRepository arancelRepository;
}

package proyecto.topEducation.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.topEducation.Entities.ArancelEntity;
import proyecto.topEducation.Entities.CuotasEntity;
import proyecto.topEducation.Repositories.ArancelRepository;
import proyecto.topEducation.Repositories.CuotasRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CuotasService {
    @Autowired
    CuotasRepository cuotasRepository;
    @Autowired
    ArancelRepository arancelRepository;

    public List<CuotasEntity> getAllCuotas() {
        return cuotasRepository.findAll();
    }

    public Optional<CuotasEntity> getCuotaById(Long id) {
        return cuotasRepository.findById(id);
    }

    public CuotasEntity createCuota(CuotasEntity cuota) {
        return cuotasRepository.save(cuota);
    }



    public void deleteCuota(Long id) {
        cuotasRepository.deleteById(id);
    }
    public List<CuotasEntity> obtenerCuotasPorArancel(ArancelEntity arancel) {
        return cuotasRepository.findByArancelId(arancel.getId_arancel());
    }
}

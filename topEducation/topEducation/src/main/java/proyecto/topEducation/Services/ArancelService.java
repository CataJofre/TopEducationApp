package proyecto.topEducation.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.topEducation.Entities.ArancelEntity;
import proyecto.topEducation.Repositories.ArancelRepository;
import proyecto.topEducation.Repositories.CuotasRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ArancelService {
    @Autowired
    ArancelRepository arancelRepository;
    @Autowired
    CuotasRepository cuotasRepository;
    // Operación para crear un arancel
    public ArancelEntity crearArancel(ArancelEntity arancel) {
        int dctoTipoColegio= arancel.getDcto_colegio_procedencia();
        int dctoTiempoEgreso= arancel.getDcto_tiempo_egreso();
        double descuentoTotal = 1.0 - (dctoTipoColegio / 100.0) - (dctoTiempoEgreso / 100.0);
        double montoDespuesDescuento = 1500000 * descuentoTotal;
        arancel.setMonto_pagar((int)montoDespuesDescuento);
        arancel.setDcto_tipo_pago(0);
        return arancelRepository.save(arancel);
    }


    // Operación para eliminar un arancel por ID
    public void eliminarArancelPorId(Long id) {
        arancelRepository.deleteById(id);
    }

    // Operación para obtener todos los aranceles
    public List<ArancelEntity> obtenerTodosLosAranceles() {
        return arancelRepository.findAll();
    }
    public Optional<ArancelEntity> obtenerArancelPorId(Long id) {
        return arancelRepository.findById(id);
    }


}

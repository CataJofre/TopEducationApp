package proyecto.topEducation.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.topEducation.Entities.ArancelEntity;
import proyecto.topEducation.Entities.CuotasEntity;
import proyecto.topEducation.Entities.EstudianteEntity;
import proyecto.topEducation.Repositories.ArancelRepository;
import proyecto.topEducation.Repositories.CuotasRepository;

import java.time.LocalDate;
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
    public List<CuotasEntity> buscarCuotasPorRutEstudiante(EstudianteEntity cuotas) {
        return cuotasRepository.findByRutEstudiante(cuotas.getRut_estudiante());
    }

    public void generarCuotasParaEstudiante(EstudianteEntity cuotas) {
        ArancelEntity arancel = arancelRepository.findByRutEstudiante(cuotas.getRut_estudiante());

        if (arancel != null) {
            int cantidadCuotas = arancel.getCantidad_cuotas();

            LocalDate fechaActual = LocalDate.now();
            LocalDate fechaCuota = fechaActual.withDayOfMonth(5);
            for (int i = 1; i <= cantidadCuotas; i++) {
                CuotasEntity cuota = new CuotasEntity();
                cuota.setArancelId(arancel);
                cuota.setRut_estudiante(arancel.getRut_estudiante());
                cuota.setEstadoCuota("Pendiente");
                cuota.setCuotas_totales(cantidadCuotas);
                cuota.setValor_de_cuota(arancel.getMonto_pagar() / cantidadCuotas);
                cuota.setFechaPago(fechaCuota.plusMonths(i));
                cuota.setDcto_media_examenes(0);
                cuota.setCuotas_pagadas(0);
                cuota.setInteres_aplicado(0);
                cuotasRepository.save(cuota);
            }
        }



    }

}

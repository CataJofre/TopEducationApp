package proyecto.topEducation.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.topEducation.Entities.ArancelEntity;
import proyecto.topEducation.Entities.CuotasEntity;
import proyecto.topEducation.Entities.EstudianteEntity;
import proyecto.topEducation.Repositories.ArancelRepository;
import proyecto.topEducation.Repositories.CuotasRepository;
import proyecto.topEducation.Repositories.PruebaRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class CuotasService {
    @Autowired
    CuotasRepository cuotasRepository;
    @Autowired
    ArancelRepository arancelRepository;
    @Autowired
    PruebaService pruebaService;

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
    public void procesarCuotasVencidas() {
        LocalDate fechaActual = LocalDate.now();
        List<CuotasEntity> cuotasPendientes = cuotasRepository.findByEstadoCuota("Pendiente"); // Obtener cuotas pendientes

        for (CuotasEntity cuota : cuotasPendientes) {
            LocalDate fechaPago = cuota.getFechaPago();
            int mesesAtraso = Period.between(fechaPago, fechaActual).getMonths();

            if (mesesAtraso > 0) {
                // Aplicar intereses según la cantidad de meses de atraso
                int interesPorcentaje = obtenerInteresPorMesesAtraso(mesesAtraso);
                int valorConInteres = cuota.getValor_de_cuota()+cuota.getValor_de_cuota()*interesPorcentaje/100;

                cuota.setInteres_aplicado(interesPorcentaje);
                cuota.setValor_de_cuota(valorConInteres);
                cuota.setEstadoCuota("Vencida");
            }

            cuotasRepository.save(cuota); // Guardar la cuota actualizada en la base de datos
        }
    }

    private int obtenerInteresPorMesesAtraso(int mesesAtraso) {
        if (mesesAtraso == 1) {
            return 3; // 3% de interés para 1 mes de atraso
        } else if (mesesAtraso == 2) {
            return 6; // 6% de interés para 2 meses de atraso
        } else if (mesesAtraso == 3) {
            return 9; // 9% de interés para 3 meses de atraso
        } else {
            return 15; // 15% de interés para más de 3 meses de atraso
        }
    }
    public void registrarPago(List<Long> cuotasPagadasIds) {
        for (Long cuotaId : cuotasPagadasIds) {
            CuotasEntity cuota = cuotasRepository.findById(cuotaId).orElse(null);
            if (cuota != null) {
                cuota.setEstadoCuota("Pagada");
                cuota.setCuotas_pagadas(cuota.getCuotas_pagadas() + 1);
                cuotasRepository.save(cuota);
            }
        }
    }
    public void aplicarDescuentosEnCuotasPendientes() {
        List<CuotasEntity> cuotasPendientes = cuotasRepository.findByEstadoCuota("Pendiente");
        for (CuotasEntity cuota : cuotasPendientes) {
            int descuentoEstudiante = cuota.getArancelId().getDcto_media_examenes();
            double descuentoAplicado = (double) descuentoEstudiante;
            cuota.setDcto_media_examenes(descuentoEstudiante);
            double valorConDescuento = cuota.getValor_de_cuota() * (1 - descuentoAplicado / 100);
            int valorRedondeado = (int) Math.round(valorConDescuento);
            cuota.setValor_de_cuota(valorRedondeado);
            cuotasRepository.save(cuota);
        }
    }



    public void calcularDescuento(){
        pruebaService.calcularPromedioYDescuentoPorMes();
    }
}

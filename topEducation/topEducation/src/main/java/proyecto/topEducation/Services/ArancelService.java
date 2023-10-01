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
public class ArancelService {
    @Autowired
    ArancelRepository arancelRepository;
    @Autowired
    CuotasRepository cuotasRepository;
    // Operación para crear un arancel
    public ArancelEntity crearArancel(ArancelEntity arancel) {
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

    // Operación para obtener un arancel por ID
    public Optional<ArancelEntity> obtenerArancelPorId(Long id) {
        return arancelRepository.findById(id);
    }
    public void generarCuotas(ArancelEntity arancel){
        EstudianteEntity rut_estudiante= arancel.getRut_estudiante();
        int cantidadCuotas= arancel.getCantidad_cuotas();
        int dctoTipoColegio= arancel.getDcto_colegio_procedencia();
        int dctoTiempoEgreso= arancel.getDcto_tiempo_egreso();
        // Calcular el descuento total
        double descuentoTotal = 1.0 - (dctoTipoColegio / 100.0) - (dctoTiempoEgreso / 100.0);

        // Calcular el monto después del descuento
        double montoDespuesDescuento = 1500000 * descuentoTotal;

        // Calcular el valor de cada cuota
        int valorCuota = (int)(montoDespuesDescuento / cantidadCuotas);

        arancel.setMonto_pagar((int)montoDespuesDescuento);
        arancel.setDcto_tipo_pago(0);
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaCuota = fechaActual.withDayOfMonth(5);
        for (int i = 0; i < cantidadCuotas; i++) {
            CuotasEntity cuota = new CuotasEntity();
            cuota.setArancelId(arancel);
            cuota.setEstado_cuota("Pendiente");
            cuota.setFecha_pago(fechaCuota.plusMonths(i));
            cuota.setCuotas_totales(cantidadCuotas);
            cuota.setInteres_aplicado(0);
            cuota.setCuotas_pagadas(0);
            cuota.setValor_de_cuota(valorCuota);
            cuota.setDcto_media_examenes(0);
            cuota.setRut_estudiante(rut_estudiante);
            cuotasRepository.save(cuota);
        }
    }
    public void pagoContado(ArancelEntity arancel){
        int montoDespuesDescuento = 1500000/2;
        arancel.setMonto_pagar(montoDespuesDescuento);
    }
}

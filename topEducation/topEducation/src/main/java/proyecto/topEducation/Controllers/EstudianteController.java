package proyecto.topEducation.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyecto.topEducation.Entities.EstudianteEntity;
import proyecto.topEducation.Services.EstudianteService;

import java.time.LocalDate;

@Controller
@RequestMapping
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping({"/estudiantes"})
    public String obtenerEstudiantes(Model modelo) {
        modelo.addAttribute("estudiantes", estudianteService.obtenerEstudiantes());
        return "estudiantes";
    }

    @GetMapping("/estudiantes/nuevo")
    public String crearEstudianteForm(Model modelo) {
        EstudianteEntity estudiante = new EstudianteEntity();
        modelo.addAttribute("estudiante", estudiante);
        return "crear_estudiante";
    }

    @PostMapping("/estudiantes")
    public String guardarEstudiante(@ModelAttribute("estudiante") EstudianteEntity estudiante) {
        estudianteService.crearEstudiante(estudiante);
        return "redirect:/arancel/nuevo";
    }

    @GetMapping("/informacion_estudiante")
    public String registrarPagoCuotas() {
        return "informacion_estudiante";
    }

    @GetMapping("/informacion")
    public String mostrarInformacionEstudiante(@RequestParam("rut_estudiante") Long rutEstudiante, Model model) {
        String nombreEstudiante = estudianteService.obtenerNombreEstudiante(rutEstudiante);
        int nroExamenesRendidos = estudianteService.calcularNroExamenesRendidos(rutEstudiante);
        double promedioPuntajeExamenes = estudianteService.obtenerPromedioPuntajeExamenes(rutEstudiante);
        String tipoPago = estudianteService.obtenerTipoPago(rutEstudiante);
        int nroTotalCuotasPactadas = estudianteService.obtenerNroTotalCuotasPactadas(rutEstudiante);
        int nroCuotasPagadas = estudianteService.calcularNroCuotasPagadas(rutEstudiante);
        int montoTotalPagado = estudianteService.calcularMontoTotalPagado(rutEstudiante);
        LocalDate fechaUltimoPago = estudianteService.obtenerFechaUltimoPago(rutEstudiante);
        int saldoPorPagar = estudianteService.calcularSaldoPorPagar(rutEstudiante);
        int nroCuotasRetraso = estudianteService.calcularNroCuotasRetraso(rutEstudiante);
        int montoTotalArancel = estudianteService.calcularMontoTotalArancel(rutEstudiante);
        model.addAttribute("rutEstudiante", rutEstudiante);
        model.addAttribute("nombreEstudiante", nombreEstudiante);
        model.addAttribute("nroExamenesRendidos", nroExamenesRendidos);
        model.addAttribute("promedioPuntajeExamenes", promedioPuntajeExamenes);
        model.addAttribute("tipoPago", tipoPago);
        model.addAttribute("nroTotalCuotasPactadas", nroTotalCuotasPactadas);
        model.addAttribute("nroCuotasPagadas", nroCuotasPagadas);
        model.addAttribute("montoTotalPagado", montoTotalPagado);
        model.addAttribute("fechaUltimoPago", fechaUltimoPago);
        model.addAttribute("saldoPorPagar", saldoPorPagar);
        model.addAttribute("nroCuotasRetraso", nroCuotasRetraso);
        model.addAttribute("montoTotalArancel", montoTotalArancel);

        return "informacion_estudiante"; // El nombre de la vista Thymeleaf
    }
}

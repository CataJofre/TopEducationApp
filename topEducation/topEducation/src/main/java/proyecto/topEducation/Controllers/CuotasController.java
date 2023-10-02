package proyecto.topEducation.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyecto.topEducation.Entities.CuotasEntity;
import proyecto.topEducation.Entities.EstudianteEntity;
import proyecto.topEducation.Services.CuotasService;

import java.util.List;

@Controller
@RequestMapping
public class CuotasController {
    @Autowired
    CuotasService cuotasService;
    @GetMapping({"/cuotas"})
    public String obtenerCuotas(Model modelo){
        modelo.addAttribute("cuotas",cuotasService.getAllCuotas());
        return "cuotas";
    }
    @PostMapping("/generarCuotas")
    public String generarCuotasParaEstudiante(@RequestParam("rut_estudiante") EstudianteEntity rutEstudiante) {
        // Llama al servicio para generar las cuotas
        cuotasService.generarCuotasParaEstudiante(rutEstudiante);
        return "cuotas";
    }



    @GetMapping("/buscar-cuotas")
    public String buscarCuotasPorEstudiante(@RequestParam("rut_estudiante") EstudianteEntity rutEstudiante, Model model) {

        List<CuotasEntity> cuotas = cuotasService.buscarCuotasPorRutEstudiante(rutEstudiante);
        model.addAttribute("cuotas", cuotas);
        return "cuotas_generadas";
    }

}

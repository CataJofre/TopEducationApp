package proyecto.topEducation.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping("/buscar-cuotas")
    public String buscarCuotasPorEstudiante(@RequestParam("rut_estudiante") EstudianteEntity rutEstudiante, Model model) {
        // Lógica para buscar cuotas por Rut de estudiante y enviar resultados al modelo
        List<CuotasEntity> cuotas = cuotasService.buscarCuotasPorRutEstudiante(rutEstudiante);
        model.addAttribute("cuotas", cuotas);
        return "cuotas_generadas"; // Nombre de la vista para mostrar los resultados
    }

}

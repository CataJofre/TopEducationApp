package proyecto.topEducation.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import proyecto.topEducation.Entities.EstudianteEntity;
import proyecto.topEducation.Services.EstudianteService;

@Controller
@RequestMapping
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping({"/estudiantes"})
    public String obtenerEstudiantes(Model modelo){
        modelo.addAttribute("estudiantes",estudianteService.obtenerEstudiantes());
        return "estudiantes";
        }
    @GetMapping("/estudiantes/nuevo")
    public String crearEstudianteForm(Model modelo){
        EstudianteEntity estudiante= new EstudianteEntity();
        modelo.addAttribute("estudiante", estudiante);
        return "crear_estudiante";
        }
    @PostMapping("/estudiantes")
    public String guardarEstudiante(@ModelAttribute("estudiante")EstudianteEntity estudiante){
        estudianteService.crearEstudiante(estudiante);
        return "redirect:/arancel/nuevo";
    }

}

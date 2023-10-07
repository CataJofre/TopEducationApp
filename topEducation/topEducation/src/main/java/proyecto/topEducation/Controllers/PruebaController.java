package proyecto.topEducation.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import proyecto.topEducation.Entities.PruebaEntity;
import proyecto.topEducation.Services.PruebaService;

import java.util.List;

@Controller
@RequestMapping
public class PruebaController {
    @Autowired
    PruebaService pruebaService;

    @GetMapping("/examenes")
    public String mostrarNotasDeExamenes(Model model) {
        List<PruebaEntity> examen = pruebaService.obtenerTodasLasPruebas();
        model.addAttribute("examen", examen);
        return "examenes"; // Nombre de la vista HTML
    }
    @PostMapping("/examenes")
    public String procesarArchivo(@RequestParam("file") MultipartFile file) {
        try {
            pruebaService.procesarArchivoCSV(file);
            return "redirect:/examenes"; // Página de éxito
        } catch (Exception e) {
            e.printStackTrace(); // Agrega esta línea para imprimir la excepción
            return "redirect:/error"; // Página de error
        }
    }


}

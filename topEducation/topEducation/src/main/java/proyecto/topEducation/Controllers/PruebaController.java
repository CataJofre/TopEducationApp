package proyecto.topEducation.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import proyecto.topEducation.Services.PruebaService;

@Controller
@RequestMapping
public class PruebaController {
    @Autowired
    PruebaService pruebaService;

    @GetMapping("/importar")
    public String cargarArchivo() {
        return "importar_notas";
    }

    @PostMapping("/importar")
    public String procesarArchivo(@RequestParam("file") MultipartFile file) {
        try {
            pruebaService.procesarArchivoCSV(file);
            return "redirect:/exito"; // Página de éxito
        } catch (Exception e) {
            return "redirect:/error"; // Página de error
        }
    }

}

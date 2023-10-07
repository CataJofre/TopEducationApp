package proyecto.topEducation.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import proyecto.topEducation.Entities.ArancelEntity;
import proyecto.topEducation.Services.ArancelService;
import proyecto.topEducation.Services.CuotasService;

@Controller
@RequestMapping
public class ArancelController {
    @Autowired
    ArancelService arancelService;
@Autowired
    CuotasService cuotasService;
    @GetMapping({"/arancel"})
    public String obtenerAranceles(Model modelo){
        modelo.addAttribute("arancel",arancelService.obtenerTodosLosAranceles());
        return "arancel";
    }
    @GetMapping("/arancel/nuevo")
    public String matricularEstudianteForm(Model modelo){
        ArancelEntity arancel= new ArancelEntity();
        modelo.addAttribute("arancel", arancel);
        return "arancel_descuentos";
    }
    @PostMapping("/arancel")
    public String guardarArancel(@ModelAttribute("arancel")ArancelEntity arancel, Model model){
        arancelService.crearArancel(arancel);
        return "generar_cuotas";
    }



}

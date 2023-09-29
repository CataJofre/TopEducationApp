package proyecto.topEducation.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import proyecto.topEducation.Entities.ArancelEntity;
import proyecto.topEducation.Entities.CuotasEntity;
import proyecto.topEducation.Services.ArancelService;
import proyecto.topEducation.Services.CuotasService;

import java.util.List;
import java.util.Optional;

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
        return "arancel_matricula";
    }
    @PostMapping("/arancel")
    public String guardarEstudiante(@ModelAttribute("arancel")ArancelEntity arancel,Model model){
        arancelService.crearArancel(arancel);
        arancelService.generarCuotas(arancel);
        List<CuotasEntity> cuotas = cuotasService.obtenerCuotasPorArancel(arancel);
        model.addAttribute("cuotas", cuotas);
        return "arancel_resultado";


    }
    @GetMapping("/arancel/{id}")
    public String obtenerCuotas(@PathVariable Long arancelId, Model model) {
        Optional<ArancelEntity> optionalArancel = arancelService.obtenerArancelPorId(arancelId);

        if (optionalArancel.isPresent()) {
            ArancelEntity arancel = optionalArancel.get();
            List<CuotasEntity> cuotas = cuotasService.obtenerCuotasPorArancel(arancel);
            model.addAttribute("cuota", cuotas);
            return "arancel_resultado";
        } else {
            return "error";
        }
    }

}

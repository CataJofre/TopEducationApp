package proyecto.topEducation.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import proyecto.topEducation.Services.CuotasService;

@Controller
@RequestMapping
public class CuotasController {
    @Autowired
    CuotasService cuotasService;
}

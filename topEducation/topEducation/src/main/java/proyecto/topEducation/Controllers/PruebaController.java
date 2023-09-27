package proyecto.topEducation.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import proyecto.topEducation.Services.PruebaService;

@Controller
@RequestMapping
public class PruebaController {
    @Autowired
    PruebaService pruebaService;
}

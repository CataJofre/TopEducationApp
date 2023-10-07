package proyecto.topEducation.Controllers;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

public class indexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @ExceptionHandler(Exception.class)
    public String handleGenericException(HttpServletRequest request, Model model) {
        model.addAttribute("error", "Ocurrió un error inesperado.");
        return "error";
    }
}

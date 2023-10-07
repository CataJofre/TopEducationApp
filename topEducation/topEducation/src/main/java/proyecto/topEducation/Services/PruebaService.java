package proyecto.topEducation.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import proyecto.topEducation.Entities.EstudianteEntity;
import proyecto.topEducation.Entities.PruebaEntity;
import proyecto.topEducation.Repositories.PruebaRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PruebaService {
    @Autowired
    PruebaRepository pruebaRepository;
    public void procesarArchivoCSV(MultipartFile file) throws IOException {
        // Leer el archivo CSV y guardar los datos en la base de datos
        List<PruebaEntity> pruebas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                if (datos.length == 4) {
                    PruebaEntity prueba = new PruebaEntity();
                    prueba.setId_prueba(Long.parseLong(datos[0]));
                    prueba.setRut_estudiante(new EstudianteEntity(datos[1]));
                    prueba.setPuntaje_obtenido(Integer.parseInt(datos[2]));
                    prueba.setFecha_examen(LocalDate.parse(datos[3]));
                    pruebas.add(prueba);
                }
            }
        }
        pruebaRepository.saveAll(pruebas);
    }
}

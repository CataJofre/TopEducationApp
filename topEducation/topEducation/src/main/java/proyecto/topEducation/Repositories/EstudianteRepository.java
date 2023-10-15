package proyecto.topEducation.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.topEducation.Entities.EstudianteEntity;

@Repository
public interface EstudianteRepository extends JpaRepository<EstudianteEntity, Long> {
    @Query("SELECT e FROM EstudianteEntity e WHERE e.rut_estudiante = :rut_estudiante")
    EstudianteEntity findEstudiantePorId(@Param("rut_estudiante") Long rut_estudiante);

}

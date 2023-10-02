package proyecto.topEducation.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.topEducation.Entities.ArancelEntity;

@Repository
public interface ArancelRepository extends JpaRepository<ArancelEntity, Long> {
 /*   ArancelEntity findByRutEstudiante(Long rutEstudiante);

*/
    @Query("SELECT a FROM ArancelEntity a WHERE a.rut_estudiante.rut_estudiante = :rut_estudiante")
    ArancelEntity findByRutEstudiante(@Param("rut_estudiante") Long rut_estudiante);
}

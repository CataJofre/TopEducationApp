package proyecto.topEducation.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.topEducation.Entities.CuotasEntity;

import java.util.List;

@Repository
public interface CuotasRepository extends JpaRepository<CuotasEntity, Long> {
    @Query("SELECT c FROM CuotasEntity c WHERE c.arancelId.id_arancel = :arancelId")
    List<CuotasEntity> findByArancelId(@Param("arancelId") Long arancelId);

    @Query("SELECT c FROM CuotasEntity c WHERE c.rut_estudiante.rut_estudiante = :rut_estudiante")
    List<CuotasEntity> findByRutEstudiante(@Param("rut_estudiante") Long rut_estudiante);


    List<CuotasEntity> findByEstadoCuota(String pendiente);
}


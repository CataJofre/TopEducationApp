package proyecto.topEducation.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.topEducation.Entities.CuotasEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CuotasRepository extends JpaRepository<CuotasEntity, Long> {
    @Query("SELECT c FROM CuotasEntity c WHERE c.arancelId.id_arancel = :arancelId")
    List<CuotasEntity> findByArancelId(@Param("arancelId") Long arancelId);

    @Query("SELECT c FROM CuotasEntity c WHERE c.rut_estudiante.rut_estudiante = :rut_estudiante")
    List<CuotasEntity> findByRutEstudiante(@Param("rut_estudiante") Long rut_estudiante);

    List<CuotasEntity> findByEstadoCuota(String pendiente);

    @Query("SELECT MAX(c.fechaPago) FROM CuotasEntity c WHERE c.rut_estudiante.rut_estudiante = :rut_estudiante AND c.estadoCuota = :estadoCuota")
    LocalDate findMaxFechaPagoByRutEstudianteAndEstadoCuota(long rut_estudiante, String estadoCuota);

    @Query("SELECT SUM(c.valor_de_cuota) " + "FROM CuotasEntity c " + "WHERE c.rut_estudiante.rut_estudiante = :rut_estudiante " + "AND c.estadoCuota IN ('Pendiente', 'Vencida')")
    int sumSaldoPorPagar(@Param("rut_estudiante") Long rut_estudiante);

    @Query("SELECT COUNT(c) " + "FROM CuotasEntity c " + "WHERE c.rut_estudiante.rut_estudiante = :rut_estudiante " + "AND c.estadoCuota = 'Vencida'")
    int countCuotasVencidasByRutEstudiante(@Param("rut_estudiante") Long rut_estudiante);

    @Query("SELECT COUNT(c) FROM CuotasEntity c WHERE c.rut_estudiante.rut_estudiante = :rut_estudiante AND c.estadoCuota = 'Pagada'")
    int countCuotasPagadasByRutEstudiante(@Param("rut_estudiante") Long rut_estudiante);

    @Query("SELECT SUM(c.valor_de_cuota) " + "FROM CuotasEntity c " + "WHERE c.rut_estudiante.rut_estudiante = :rut_estudiante " + "AND c.estadoCuota = 'Pagada'")
    int sumMontoTotalPagadoByRutEstudiante(@Param("rut_estudiante") Long rut_estudiante);

}


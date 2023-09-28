package proyecto.topEducation.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.topEducation.Entities.CuotasEntity;

@Repository
public interface CuotasRepository extends JpaRepository<CuotasEntity, Long> {
}

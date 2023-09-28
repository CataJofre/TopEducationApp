package proyecto.topEducation.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.topEducation.Entities.ArancelEntity;

@Repository
public interface ArancelRepository extends JpaRepository<ArancelEntity, Long> {
}

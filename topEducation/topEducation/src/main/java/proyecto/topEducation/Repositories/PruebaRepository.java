package proyecto.topEducation.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.topEducation.Entities.PruebaEntity;

@Repository
public interface PruebaRepository extends JpaRepository<PruebaEntity, Long> {
}

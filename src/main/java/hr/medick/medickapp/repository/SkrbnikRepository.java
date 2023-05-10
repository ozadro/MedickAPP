package hr.medick.medickapp.repository;

import hr.medick.medickapp.model.Skrbnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkrbnikRepository extends JpaRepository<Skrbnik, Long> {
    boolean existsSkrbnikByOsobaId(Long id);

    Skrbnik findSkrbnikById(Long id);

    Skrbnik findSkrbnikByOsobaEmail(String email);


}

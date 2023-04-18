package hr.medick.medickapp.repository;

import hr.medick.medickapp.model.SkrbnikPacijent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkrbnikPacijentRepository extends JpaRepository<SkrbnikPacijent, Long> {
}

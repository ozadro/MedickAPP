package hr.medick.medickapp.repository;

import hr.medick.medickapp.model.Pacijent;
import hr.medick.medickapp.model.Skrbnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacijentRepository extends JpaRepository<Pacijent, Long> {
}

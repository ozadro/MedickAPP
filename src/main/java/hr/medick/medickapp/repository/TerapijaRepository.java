package hr.medick.medickapp.repository;

import hr.medick.medickapp.model.Terapija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerapijaRepository extends JpaRepository<Terapija, Long> {
}

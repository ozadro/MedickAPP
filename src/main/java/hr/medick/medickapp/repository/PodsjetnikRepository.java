package hr.medick.medickapp.repository;

import hr.medick.medickapp.model.Podsjetnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PodsjetnikRepository extends JpaRepository<Podsjetnik, Long> {
    List<Podsjetnik> findAllByTerapijaId (Long id);
    Podsjetnik findByTerapijaId (Long id);
}

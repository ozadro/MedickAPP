package hr.medick.medickapp.repository;

import hr.medick.medickapp.model.SkrbnikPacijent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkrbnikPacijentRepository extends JpaRepository<SkrbnikPacijent, Long> {

    List<SkrbnikPacijent> findSkrbnikPacijentsBySkrbnikId(Long id);
    Boolean existsSkrbnikPacijentByPacijentId(Long id);
}

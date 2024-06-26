package hr.medick.medickapp.repository;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.model.Pacijent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacijentRepository extends JpaRepository<Pacijent, Long> {
    boolean existsPacijentByOsobaId(Long id);

    Pacijent findPacijentByOsobaId(Long id);

    Pacijent findPacijentByOsoba(Osoba osoba);
}

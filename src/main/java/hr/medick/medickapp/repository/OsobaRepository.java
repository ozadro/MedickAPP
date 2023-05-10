package hr.medick.medickapp.repository;

import hr.medick.medickapp.model.Osoba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OsobaRepository extends JpaRepository<Osoba, Long> {
        Osoba findByEmailContaining(String email);
        List<Osoba> findOsobasByIme(String query);
        List<Osoba> findOsobasByPrezime(String query);
        List<Osoba> findOsobasByImeAndPrezime(String ime, String prezime);
        Boolean existsOsobaByEmail(String email);
}

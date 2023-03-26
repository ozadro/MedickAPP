package hr.medick.medickapp.repository;

import hr.medick.medickapp.model.Osoba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OsobaRepository extends JpaRepository<Osoba, Long> {
        List<Osoba> findByEmailContaining(String email);

}

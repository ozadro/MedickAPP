package hr.medick.medickapp.repository;


import hr.medick.medickapp.model.Lijek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LijekRepository extends JpaRepository<Lijek, Long> {
    boolean existsLijekByNaziv(String imeLijek);
    Lijek findLijekByNaziv(String imeLijek);
}

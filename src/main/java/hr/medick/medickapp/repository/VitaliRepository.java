package hr.medick.medickapp.repository;

import hr.medick.medickapp.model.Vitali;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VitaliRepository extends JpaRepository<Vitali, Long> {
    List<Vitali> findAllByPacijentId(Long id);
}

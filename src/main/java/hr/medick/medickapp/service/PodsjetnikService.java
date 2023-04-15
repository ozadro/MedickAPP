package hr.medick.medickapp.service;

import hr.medick.medickapp.model.Podsjetnik;
import hr.medick.medickapp.repository.PodsjetnikRepository;
import org.springframework.stereotype.Service;

@Service
public class PodsjetnikService {

    private final PodsjetnikRepository podsjetnikRepository;

    public PodsjetnikService(PodsjetnikRepository podsjetnikRepository) {
        this.podsjetnikRepository = podsjetnikRepository;
    }

    public void savePodsjetnik(Podsjetnik podsjetnik){
        podsjetnikRepository.save(podsjetnik);
    }
}

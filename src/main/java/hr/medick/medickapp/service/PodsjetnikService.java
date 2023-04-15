package hr.medick.medickapp.service;

import hr.medick.medickapp.model.Podsjetnik;
import hr.medick.medickapp.model.Terapija;
import hr.medick.medickapp.repository.PodsjetnikRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PodsjetnikService {

    private final PodsjetnikRepository podsjetnikRepository;

    public PodsjetnikService(PodsjetnikRepository podsjetnikRepository) {
        this.podsjetnikRepository = podsjetnikRepository;
    }

    public void savePodsjetnik(Podsjetnik podsjetnik){
        podsjetnikRepository.save(podsjetnik);
    }

    public Podsjetnik getReminderByTerapija(Terapija terapija) {
        return podsjetnikRepository.findByTerapijaId(terapija.getId());
    }
}

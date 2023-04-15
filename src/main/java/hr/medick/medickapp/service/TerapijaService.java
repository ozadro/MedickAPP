package hr.medick.medickapp.service;

import hr.medick.medickapp.model.Terapija;
import hr.medick.medickapp.repository.TerapijaRepository;
import org.springframework.stereotype.Service;

@Service
public class TerapijaService {

    private final TerapijaRepository terapijaRepository;

    public TerapijaService(TerapijaRepository terapijaRepository) {
        this.terapijaRepository = terapijaRepository;
    }

    public void saveTerapija(Terapija terapija) {
        terapijaRepository.save(terapija);
    }
}

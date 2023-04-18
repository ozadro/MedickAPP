package hr.medick.medickapp.service;

import hr.medick.medickapp.model.Lijek;
import hr.medick.medickapp.repository.LijekRepository;
import org.springframework.stereotype.Service;

@Service
public class LijekService {

    private final LijekRepository lijekRepository;

    public LijekService(LijekRepository lijekRepository) {
        this.lijekRepository = lijekRepository;
    }

    public void saveLijek(Lijek lijek) {
        lijekRepository.save(lijek);
    }

    public boolean existsLijekByName(String imeLijek){
        return lijekRepository.existsLijekByNaziv(imeLijek);
    }

    public Lijek getLijekByName(String imeLijek){
        return lijekRepository.findLijekByNaziv(imeLijek);
    }
}

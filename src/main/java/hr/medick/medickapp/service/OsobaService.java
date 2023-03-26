package hr.medick.medickapp.service;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.repository.OsobaRepository;
import org.springframework.stereotype.Service;

@Service
public class OsobaService {
    private OsobaRepository osobaRepository;

    public OsobaService(OsobaRepository osobaRepository) {
        this.osobaRepository = osobaRepository;
    }

    public Long saveOsoba(Osoba osoba){
        osobaRepository.save(osoba);
        return osoba.getId();
    }
}

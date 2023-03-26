package hr.medick.medickapp.service;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.repository.OsobaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OsobaService {
    @Autowired
    private OsobaRepository osobaRepository;

    public void saveOsoba(Osoba osoba){
        osobaRepository.save(osoba);
    }
}

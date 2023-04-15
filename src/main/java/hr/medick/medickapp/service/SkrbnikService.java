package hr.medick.medickapp.service;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.model.Skrbnik;
import hr.medick.medickapp.repository.SkrbnikRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkrbnikService {
    private final OsobaService osobaService;
    private final SkrbnikRepository skrbnikRepository;

    public SkrbnikService(OsobaService osobaService, SkrbnikRepository skrbnikRepository) {
        this.osobaService = osobaService;
        this.skrbnikRepository = skrbnikRepository;
    }

    public void saveSkrbnik(Skrbnik skrbnik){
        Long osobaID = osobaService.saveOsoba(skrbnik.getOsoba());
        skrbnik.getOsoba().setId(osobaID);
        skrbnikRepository.save(skrbnik);
    }
    public Osoba getOsobaWithEmail(String email){
        return osobaService.getOsobaWithThatEmail(email);
    }
    public List<Osoba> getAllOsobaSkrbnik(){
        return osobaService.getAllOsoba();
    }
    public boolean isSkrbnik(Long osobaid){
        return skrbnikRepository.existsSkrbnikByOsobaId(osobaid);
    }
}

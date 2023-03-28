package hr.medick.medickapp.service;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.model.Skrbnik;
import hr.medick.medickapp.repository.SkrbnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkrbnikService {
    @Autowired
    private OsobaService osobaService;
    private final SkrbnikRepository skrbnikRepository;

    public SkrbnikService(SkrbnikRepository skrbnikRepository) {
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
    public List<Osoba> getAllOsoba(){
        return osobaService.getAllOsoba();
    }
}

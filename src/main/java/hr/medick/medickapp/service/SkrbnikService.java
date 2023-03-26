package hr.medick.medickapp.service;

import hr.medick.medickapp.model.Skrbnik;
import hr.medick.medickapp.repository.SkrbnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkrbnikService {
    @Autowired
    private OsobaService osobaService;
    private SkrbnikRepository skrbnikRepository;

    public SkrbnikService(SkrbnikRepository skrbnikRepository) {
        this.skrbnikRepository = skrbnikRepository;
    }

    public void saveSkrbnik(Skrbnik skrbnik){
        Long osobaID = osobaService.saveOsoba(skrbnik.getOsoba());
        skrbnik.getOsoba().setId(osobaID);
        skrbnikRepository.save(skrbnik);
    }
}

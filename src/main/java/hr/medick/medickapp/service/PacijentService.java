package hr.medick.medickapp.service;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.model.Pacijent;
import hr.medick.medickapp.repository.PacijentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacijentService {

    private final OsobaService osobaService;

    private final PacijentRepository pacijentRepository;

    public PacijentService(OsobaService osobaService, PacijentRepository pacijentRepository) {
        this.osobaService = osobaService;
        this.pacijentRepository = pacijentRepository;
    }

    public void savePacijent(Pacijent pacijent) {
        Long osobaID = osobaService.saveOsoba(pacijent.getOsoba());
        pacijent.getOsoba().setId(osobaID);
        pacijentRepository.save(pacijent);
    }

    public List<Osoba> getAllOsobaPacijent() {
        return osobaService.getAllOsoba();
    }

    public boolean isPacijent(Long osobaid) {
        return pacijentRepository.existsPacijentByOsobaId(osobaid);
    }

    public Osoba getOsobaWithEmail(String email) {
        return osobaService.getOsobaWithThatEmail(email);
    }

    public Pacijent getPacijentById(Long id){
        return pacijentRepository.findPacijentById(id);
    }

    public Pacijent findPacijentByOsobaEmail(String email){
        Osoba osoba = osobaService.getOsobaWithThatEmail(email);
        return pacijentRepository.findPacijentByOsoba(osoba);
    }
}

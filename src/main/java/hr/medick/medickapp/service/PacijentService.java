package hr.medick.medickapp.service;

import hr.medick.medickapp.model.Pacijent;
import hr.medick.medickapp.repository.PacijentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacijentService {

    @Autowired
    private OsobaService osobaService;

    private PacijentRepository pacijentRepository;

    public PacijentService(PacijentRepository pacijentRepository) {
        this.pacijentRepository = pacijentRepository;
    }

    public void savePacijent(Pacijent pacijent){
        Long osobaID = osobaService.saveOsoba(pacijent.getOsoba());
        pacijent.getOsoba().setId(osobaID);
        pacijentRepository.save(pacijent);
    }
}

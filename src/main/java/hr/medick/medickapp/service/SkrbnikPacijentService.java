package hr.medick.medickapp.service;

import hr.medick.medickapp.model.SkrbnikPacijent;
import hr.medick.medickapp.repository.SkrbnikPacijentRepository;
import org.springframework.stereotype.Service;

@Service
public class SkrbnikPacijentService {

    private final SkrbnikPacijentRepository skrbnikPacijentRepository;

    public SkrbnikPacijentService(SkrbnikPacijentRepository skrbnikPacijentRepository) {
        this.skrbnikPacijentRepository = skrbnikPacijentRepository;
    }


    public void saveSkrbnikPacijent(SkrbnikPacijent skrbnikPacijent){
        skrbnikPacijentRepository.save(skrbnikPacijent);
    }





}

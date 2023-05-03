package hr.medick.medickapp.service;

import hr.medick.medickapp.model.Pacijent;
import hr.medick.medickapp.model.SkrbnikPacijent;
import hr.medick.medickapp.repository.SkrbnikPacijentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkrbnikPacijentService {

    private final SkrbnikPacijentRepository skrbnikPacijentRepository;

    public SkrbnikPacijentService(SkrbnikPacijentRepository skrbnikPacijentRepository) {
        this.skrbnikPacijentRepository = skrbnikPacijentRepository;
    }

    public void saveSkrbnikPacijent(SkrbnikPacijent skrbnikPacijent){
        skrbnikPacijentRepository.save(skrbnikPacijent);
    }

    public List<Pacijent> getAllPacijentsForSkrbnik(Long id){
        List<Pacijent> pacijentList = new ArrayList<>();
        List<SkrbnikPacijent> skrbnikPacijentList = skrbnikPacijentRepository.findSkrbnikPacijentsBySkrbnikId(id);

        for (SkrbnikPacijent skrbnikPacijent : skrbnikPacijentList) {
            pacijentList.add(skrbnikPacijent.getPacijent());
        }


        return pacijentList;
    }

    public Boolean existsSkrbnikPacijentByPacijentId(Long id){
        return skrbnikPacijentRepository.existsSkrbnikPacijentByPacijentId(id);
    }





}

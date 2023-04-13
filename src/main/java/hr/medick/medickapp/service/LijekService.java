package hr.medick.medickapp.service;

import hr.medick.medickapp.model.Lijek;
import hr.medick.medickapp.repository.LijekRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LijekService {

    private final LijekRepository lijekRepository;

    public LijekService(LijekRepository lijekRepository) {
        this.lijekRepository = lijekRepository;
    }

    public void saveLijek(Lijek lijek){
        lijekRepository.save(lijek);
    }

    public List<Lijek> getAllLijek(){
        return lijekRepository.findAll();
    }


}

package hr.medick.medickapp.service;

import hr.medick.medickapp.model.Vitali;
import hr.medick.medickapp.repository.VitaliRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VitaliService {

    private final VitaliRepository vitaliRepository;

    public VitaliService(VitaliRepository vitaliRepository) {
        this.vitaliRepository = vitaliRepository;
    }

    public void saveVitals(Vitali vitali) {
        vitaliRepository.save(vitali);
    }

    public List<Vitali> getAllVitalsByPacijentId(Long id) {
        return vitaliRepository.findAllByPacijentId(id);
    }
}

package hr.medick.medickapp.service;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.repository.OsobaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OsobaService {
    private final OsobaRepository osobaRepository;

    public OsobaService(OsobaRepository osobaRepository) {
        this.osobaRepository = osobaRepository;
    }

    public Long saveOsoba(Osoba osoba){
        osobaRepository.save(osoba);
        return osoba.getId();
    }

    public Osoba getOsobaWithThatEmail(String email){
        return osobaRepository.findByEmailContaining(email);
    }
    public List<Osoba> getAllOsoba(){
        return osobaRepository.findAll();
    }
    public List<Osoba> getOsobaByIme(String query){return osobaRepository.findOsobasByIme(query);}
    public List<Osoba> getOsobaByPrezime(String query){return osobaRepository.findOsobasByPrezime(query);}
    public List<Osoba> getOsobasByImeAndPrezime(String name, String lastName){return osobaRepository.findOsobasByImeAndPrezime(name,lastName);}

}

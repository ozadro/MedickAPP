package hr.medick.medickapp.service;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.repository.OsobaRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import hr.medick.medickapp.model.CustomUserDetails;

import java.util.List;

@Service
public class OsobaService implements UserDetailsService {
    private final OsobaRepository osobaRepository;
    private final PasswordEncoder passwordEncoder;
    @Lazy
    public OsobaService(OsobaRepository osobaRepository, PasswordEncoder passwordEncoder) {
        this.osobaRepository = osobaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Long saveOsoba(Osoba osoba){
        String encryptedPassword = passwordEncoder.encode(osoba.getLozinka());
        osoba.setLozinka(encryptedPassword);
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
    public Boolean existsOsobaByEmail(String email){
        return osobaRepository.existsOsobaByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Osoba user = getOsobaWithThatEmail(email);
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return new CustomUserDetails(user);
    }
}

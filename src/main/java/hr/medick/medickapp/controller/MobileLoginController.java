package hr.medick.medickapp.controller;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.service.PacijentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mobileLogin")
public class MobileLoginController {

    private final PacijentService pacijentService;

    public MobileLoginController(PacijentService pacijentService) {
        this.pacijentService = pacijentService;
    }

    @PostMapping()
    public ResponseEntity<Osoba> authPacijent(@RequestParam("email") String email, @RequestParam("lozinka") String lozinka)
    {
        List<Osoba> osobaList = pacijentService.getAllOsobaPacijent();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Osoba osoba = new Osoba();

            for (Osoba trazenaOsoba : osobaList) {
                boolean istina1 = trazenaOsoba.getEmail().trim().equals(email.trim());
                boolean istina2 = passwordEncoder.matches(lozinka.trim(), trazenaOsoba.getLozinka().trim());
                if (trazenaOsoba.getEmail().trim().equals(email.trim()) && passwordEncoder.matches(lozinka.trim(), trazenaOsoba.getLozinka().trim())) {
                    if (pacijentService.isPacijent(trazenaOsoba.getId())){
                        osoba = trazenaOsoba;
                        break;
                    }
                    else{
                        osoba = null;
                    }
                }
            }

        System.out.println(osoba);

        return new ResponseEntity<>(osoba, HttpStatus.OK);
    }
}

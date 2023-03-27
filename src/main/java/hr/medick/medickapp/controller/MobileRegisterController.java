package hr.medick.medickapp.controller;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.model.Pacijent;
import hr.medick.medickapp.service.PacijentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mobileRegister")
public class MobileRegisterController {

    private PacijentService pacijentService;

    public MobileRegisterController(PacijentService pacijentService) {
        this.pacijentService = pacijentService;
    }

    @PostMapping()
    public ResponseEntity<String> savePacijent(@RequestBody Osoba osoba)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(osoba.getLozinka());
        osoba.setLozinka(encodedPassword);
        Pacijent pacijent = new Pacijent(osoba);
        pacijentService.savePacijent(pacijent);

        return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
    }
}

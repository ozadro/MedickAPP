package hr.medick.medickapp.controller;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.model.Pacijent;
import hr.medick.medickapp.service.OsobaService;
import hr.medick.medickapp.service.PacijentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobileEditPacijent")
public class MobileEditPacijentController {
    private final OsobaService osobaService;

    public MobileEditPacijentController(OsobaService osobaService) {
        this.osobaService = osobaService;
    }

    @PostMapping()
    public ResponseEntity<String> saveEditPacijent(@RequestBody Osoba osoba) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(osoba.getLozinka());
        osoba.setLozinka(encodedPassword);
        osobaService.saveOsoba(osoba);

        System.out.println(osoba);

        return new ResponseEntity<>("DataSavedSuccessfully", HttpStatus.OK);
    }
}

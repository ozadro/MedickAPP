package hr.medick.medickapp.controller;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.model.Skrbnik;
import hr.medick.medickapp.service.OsobaService;
import hr.medick.medickapp.service.SkrbnikService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private SkrbnikService skrbnikService;

    public RegisterController(SkrbnikService skrbnikService) {
        this.skrbnikService = skrbnikService;
    }

    @GetMapping("/register")
    public String registreIndex(){
        return "register";
    }

    @PostMapping("/register")
    public String saveOsoba(@ModelAttribute("newOsoba")Osoba osoba)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(osoba.getLozinka());
        osoba.setLozinka(encodedPassword);
        Skrbnik skrbnik = new Skrbnik(osoba);
        skrbnikService.saveSkrbnik(skrbnik);
        return "register";
    }
}

package hr.medick.medickapp.controller;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.model.Skrbnik;
import hr.medick.medickapp.service.SkrbnikService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RegisterController {

    private final SkrbnikService skrbnikService;

    public RegisterController(SkrbnikService skrbnikService) {
        this.skrbnikService = skrbnikService;
    }

    @GetMapping("/register")
    public String registerIndex() {
        return "register";
    }

    @PostMapping("/register")
    public String saveOsoba(@ModelAttribute("newOsoba") Osoba osoba, Model model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(osoba.getLozinka());
        osoba.setLozinka(encodedPassword);
        Skrbnik skrbnik = new Skrbnik(osoba);

        Osoba osobaWithEmail = skrbnikService.getOsobaWithEmail(osoba.getEmail());

        if (osobaWithEmail == null){
            skrbnikService.saveSkrbnik(skrbnik);
            model.addAttribute("Message", "Registracija uspješna!");
            model.addAttribute("ErrorMessage", "");
        } else if (osobaWithEmail.getEmail().trim().equals(osoba.getEmail().trim())) {
            model.addAttribute("ErrorMessage", "Registracija neuspješna, email već netko koristi!");
            model.addAttribute("Message", "");
        }
        return "register";
    }
}

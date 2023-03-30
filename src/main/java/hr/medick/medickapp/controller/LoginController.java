package hr.medick.medickapp.controller;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.service.SkrbnikService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {

    private final SkrbnikService skrbnikService;

    public LoginController(SkrbnikService skrbnikService) {
        this.skrbnikService = skrbnikService;
    }
    @GetMapping("/login")
    public String loginIndex(){
        return "login";
    }

    @PostMapping("/login")
    public String authOsoba(@RequestParam("email") String email, @RequestParam("lozinka") String lozinka, Model model){
        List<Osoba> osobaList = skrbnikService.getAllOsobaSkrbnik();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (osobaList.isEmpty()) {
            model.addAttribute("ErrorMessage", "Prijva neuspješna, pogrešni podaci o prijavi!");
        } else {
        for (Osoba trazenaOsoba : osobaList) {
            if (trazenaOsoba.getEmail().trim().equals(email.trim()) && passwordEncoder.matches(lozinka.trim(), trazenaOsoba.getLozinka().trim())) {
                if (skrbnikService.isSkrbnik(trazenaOsoba.getId())){
                return "redirect:/main";
                }
            } else {
                model.addAttribute("ErrorMessage", "Prijva neuspješna, pogrešni podaci o prijavi!");
                model.addAttribute("Message", "");
            }
        }
    }

        return "login";
    }
}

package hr.medick.medickapp.controller;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.service.OsobaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

    @Autowired
    private OsobaService osobaService;

    @GetMapping("/register")
    public String registreIndex(){
        return "register";
    }

    @PostMapping("/register")
    public String saveOsoba(@ModelAttribute("newOsoba")Osoba osoba)
    {
        osobaService.saveOsoba(osoba);
        return "register";
    }
}

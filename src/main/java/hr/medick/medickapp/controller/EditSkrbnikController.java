package hr.medick.medickapp.controller;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.model.Skrbnik;
import hr.medick.medickapp.service.SkrbnikService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EditSkrbnikController {
    private final SkrbnikService skrbnikService;

    public EditSkrbnikController(SkrbnikService skrbnikService) {
        this.skrbnikService = skrbnikService;
    }
    @GetMapping("/editSkrbnik")
    public String editSkrbnikIndex(@RequestParam(name = "show")String email, Model model){
        Skrbnik skrbnik = skrbnikService.getSkrbnikByEmail(email);
        model.addAttribute("skrbnik", skrbnik);

        return "editSkrbnik";
    }

    @PostMapping("/saveSkrbnik")
    public String saveSkrbnik(@ModelAttribute Skrbnik skrbnik){

        Skrbnik skrbnikSave = skrbnikService.getSkrbnikById(skrbnik.getId());
        Osoba osoba = skrbnik.getOsoba();
        skrbnikSave.getOsoba().setIme(osoba.getIme());
        skrbnikSave.getOsoba().setPrezime(osoba.getPrezime());
        skrbnikSave.getOsoba().setAdresaStanovanja(osoba.getAdresaStanovanja());
        skrbnikSave.getOsoba().setEmail(osoba.getEmail());
        skrbnikSave.getOsoba().setTelefon(osoba.getTelefon());

        skrbnikService.saveSkrbnik(skrbnikSave);

        return "redirect:/viewSkrbnik";
    }
}

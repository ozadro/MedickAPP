package hr.medick.medickapp.controller;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.model.Pacijent;
import hr.medick.medickapp.model.Skrbnik;
import hr.medick.medickapp.service.SkrbnikPacijentService;
import hr.medick.medickapp.service.SkrbnikService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("email")
public class ShowPacijentController {
    private final SkrbnikPacijentService skrbnikPacijentService;
    private final SkrbnikService skrbnikService;
    public ShowPacijentController(SkrbnikPacijentService skrbnikPacijentService, SkrbnikService skrbnikService) {
        this.skrbnikPacijentService = skrbnikPacijentService;
        this.skrbnikService = skrbnikService;
    }

    @GetMapping("/showPatient")
    public String showPatient(Model model, ModelMap modelMap){
        List<Osoba> osobaList = new ArrayList<>();
        Skrbnik skrbnik = skrbnikService.getSkrbnikByEmail((String)modelMap.get("email"));
        List<Pacijent> pacijentList =  skrbnikPacijentService.getAllPacijentsForSkrbnik(skrbnik.getId());
        for (Pacijent pacijent : pacijentList) {
            osobaList.add(pacijent.getOsoba());
            model.addAttribute("osobaList", osobaList);
        }
        return "showPatient";
    }
}

package hr.medick.medickapp.controller;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.model.Pacijent;
import hr.medick.medickapp.service.SkrbnikPacijentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShowPacijentController {
    private final SkrbnikPacijentService skrbnikPacijentService;
    public ShowPacijentController(SkrbnikPacijentService skrbnikPacijentService) {
        this.skrbnikPacijentService = skrbnikPacijentService;
    }

    @GetMapping("/showPatient")
    public String showPatient(Model model){
        List<Osoba> osobaList = new ArrayList<>();
        List<Pacijent> pacijentList =  skrbnikPacijentService.getAllPacijentsForSkrbnik((long)1);
        for (Pacijent pacijent : pacijentList) {
            osobaList.add(pacijent.getOsoba());
            model.addAttribute("osobaList", osobaList);
        }
        return "showPatient";
    }
}

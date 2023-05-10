package hr.medick.medickapp.controller;

import hr.medick.medickapp.model.Skrbnik;
import hr.medick.medickapp.service.SkrbnikService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewSkrbnikController {

    private final SkrbnikService skrbnikService;

    public ViewSkrbnikController(SkrbnikService skrbnikService) {
        this.skrbnikService = skrbnikService;
    }

    @GetMapping("/viewSkrbnik")
    public String viewSkrbnik(Model model){

        Skrbnik skrbnik = skrbnikService.getSkrbnikById((long)1);

        model.addAttribute("skrbnik", skrbnik.getOsoba());

        return "viewSkrbnik";}

}

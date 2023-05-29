package hr.medick.medickapp.controller;

import hr.medick.medickapp.model.Skrbnik;
import hr.medick.medickapp.service.SkrbnikService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("email")
public class ViewSkrbnikController {

    private final SkrbnikService skrbnikService;

    public ViewSkrbnikController(SkrbnikService skrbnikService) {
        this.skrbnikService = skrbnikService;
    }

    @GetMapping("/viewSkrbnik")
    public String viewSkrbnik(Model model, ModelMap modelMap){


        Skrbnik skrbnik = skrbnikService.getSkrbnikByEmail((String)modelMap.get("email"));

        model.addAttribute("skrbnik", skrbnik.getOsoba());

        return "viewSkrbnik";}

}

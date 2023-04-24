package hr.medick.medickapp.controller;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.model.Pacijent;
import hr.medick.medickapp.service.OsobaService;
import hr.medick.medickapp.service.PacijentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SearchPacijentController {


    private final PacijentService pacijentService;


    private final OsobaService osobaService;

    public SearchPacijentController(PacijentService pacijentService, OsobaService osobaService) {
        this.pacijentService = pacijentService;
        this.osobaService = osobaService;
    }


    @GetMapping("/searchPatient.html")
    public String searchIndex(){return "searchPatient";}

    @PostMapping("/foundPatient.html")
    public String searchPatient(@ModelAttribute("email")String email, Model model){
    if(email.isEmpty()){
        Boolean exists;
        List<Osoba> osobaList = osobaService.getAllOsoba();
        for (Osoba osoba: osobaList) {
        exists = pacijentService.isPacijent(osoba.getId());
            if(exists){
                model.addAttribute("osobaList", osobaList);
            }
        }


    } else{
        Osoba osoba = osobaService.getOsobaWithThatEmail(email);
        Boolean exists = pacijentService.isPacijent(osoba.getId());
        if(exists) {
            model.addAttribute("osoba", osoba);
           Pacijent pacijent = pacijentService.findPacijentByOsobaEmail(email);
           addPatient(pacijent);
        }
    }
    return "searchPatient";
}
    @PostMapping("/addPatient.html")
    public String addPatient(Pacijent pacijent){

        // dodati ce pacijenta skrbniku kada se login postavi kak se spada

     return "addPatient";
    }



}

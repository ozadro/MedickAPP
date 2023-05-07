package hr.medick.medickapp.controller;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.model.Pacijent;
import hr.medick.medickapp.model.Skrbnik;
import hr.medick.medickapp.model.SkrbnikPacijent;
import hr.medick.medickapp.service.OsobaService;
import hr.medick.medickapp.service.PacijentService;
import hr.medick.medickapp.service.SkrbnikPacijentService;
import hr.medick.medickapp.service.SkrbnikService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchPacijentController {
    private final PacijentService pacijentService;
    private final OsobaService osobaService;
    private final SkrbnikService skrbnikService;
    private final SkrbnikPacijentService skrbnikPacijentService;

    public SearchPacijentController(PacijentService pacijentService, OsobaService osobaService, SkrbnikService skrbnikService, SkrbnikPacijentService skrbnikPacijentService) {
        this.pacijentService = pacijentService;
        this.osobaService = osobaService;
        this.skrbnikService = skrbnikService;
        this.skrbnikPacijentService = skrbnikPacijentService;
    }

    @GetMapping("/searchPatient")
    public String searchIndex(Model model){
        List<Osoba> osobaList = new ArrayList<>();
            for (Pacijent pacijent : pacijentService.getAllPacijent()) {
                osobaList.add(pacijent.getOsoba());
            }
            model.addAttribute("osobaList",osobaList);

        return "searchPatient";
    }

    @PostMapping("/foundPatient")
    public String searchPatient(@ModelAttribute("keyword")String keyword, Model model){
        String errorMsg = "Unijeli ste nepostojećeg pacijenta, pokušajte ponovno.";

        try {
            if (keyword.isEmpty()) {
                model.addAttribute("errorMissing", "Unesite podatke o pacijentu.");
            } else {
                List<Osoba> people = new ArrayList<>();
                for (Osoba osoba : osobaService.getOsobaByIme(keyword)){
                    if (pacijentService.isPacijent(osoba.getId())){
                        people.add(osoba);
                    }
                }
                for (Osoba osoba : osobaService.getOsobaByPrezime(keyword)){
                    if (pacijentService.isPacijent(osoba.getId())){
                        people.add(osoba);
                    }
                }
               String[] splitKeyword = keyword.split(" ");
                for (Osoba osoba : osobaService.getOsobasByImeAndPrezime(splitKeyword[0], splitKeyword[1])){
                    if (pacijentService.isPacijent(osoba.getId())){
                        people.add(osoba);
                    }
                }
                if (!people.isEmpty()){
                    for (Osoba osoba : people){
                        model.addAttribute("osobaList", osoba);
                    }
                }else {
                    model.addAttribute("error", errorMsg);
                }
            }
        }
        catch (Exception exception){
            model.addAttribute("error", errorMsg);
        }
    return "searchPatient";
}


    @PostMapping("/savePatient")
    public String savePatient(@RequestParam("email")String email, Model model){
        Pacijent pacijent = pacijentService.findPacijentByOsobaEmail(email);
        Skrbnik skrbnik = skrbnikService.getAllSkrbnik().get(0);
        SkrbnikPacijent skrbnikPacijent = new SkrbnikPacijent();

        skrbnikPacijent.setPacijent(pacijent);
        skrbnikPacijent.setSkrbnik(skrbnik);

        if (!skrbnikPacijentService.existsSkrbnikPacijentByPacijentId(pacijent.getId())){
            skrbnikPacijentService.saveSkrbnikPacijent(skrbnikPacijent);
        }else{
            String msg = "Odabrani pacijent ima skrbnika!";
            model.addAttribute("msg",msg);
        }
        return "searchPatient";
    }
}

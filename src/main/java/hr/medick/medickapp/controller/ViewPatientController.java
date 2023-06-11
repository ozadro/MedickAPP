package hr.medick.medickapp.controller;


import hr.medick.medickapp.model.Pacijent;
import hr.medick.medickapp.model.Terapija;
import hr.medick.medickapp.model.Vitali;
import hr.medick.medickapp.service.PacijentService;
import hr.medick.medickapp.service.TerapijaService;
import hr.medick.medickapp.service.VitaliService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ViewPatientController {

    private final PacijentService pacijentService;
    private final TerapijaService terapijaService;
    private final VitaliService vitaliService;
    public ViewPatientController(PacijentService pacijentService, TerapijaService terapijaService, VitaliService vitaliService) {
        this.pacijentService = pacijentService;
        this.terapijaService = terapijaService;
        this.vitaliService = vitaliService;
    }

    @GetMapping("/viewPatient")
    public String viewPatient(@RequestParam(name = "show")String email, Model model){
        Pacijent pacijent = pacijentService.findPacijentByOsobaEmail(email);
        List<Terapija> terapijaList = terapijaService.getTerapijaListById(pacijent.getId());
        List<Vitali> vitalList = vitaliService.getAllVitalsByPacijentId(pacijent.getId());
        model.addAttribute("pacijent",pacijent);
        model.addAttribute("terapija", terapijaList);
        model.addAttribute("vitali", vitalList);

        return "viewPatient";
    }
}

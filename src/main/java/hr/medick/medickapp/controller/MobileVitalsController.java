package hr.medick.medickapp.controller;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.model.Pacijent;
import hr.medick.medickapp.model.Vitali;
import hr.medick.medickapp.service.PacijentService;
import hr.medick.medickapp.service.VitaliService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/mobileVitals")
public class MobileVitalsController {

    private final PacijentService pacijentService;
    private final VitaliService vitaliService;

    public MobileVitalsController(PacijentService pacijentService, VitaliService vitaliService) {
        this.pacijentService = pacijentService;
        this.vitaliService = vitaliService;
    }

    @PostMapping()
    public ResponseEntity<List<Vitali>> getAllVitlasForPacijent(@RequestBody Osoba osoba) {

        Pacijent pacijent = getPacijentByOsoba(osoba);

        List<Vitali> vitalisList = getVitals(pacijent);

        return new ResponseEntity<>(vitalisList, HttpStatus.OK);
    }

    private List<Vitali> getVitals(Pacijent pacijent) {
        return vitaliService.getAllVitalsByPacijentId(pacijent.getId());
    }

    private Pacijent getPacijentByOsoba(Osoba osoba) {
        return pacijentService.getPacijentById(osoba.getId());
    }
}

package hr.medick.medickapp.controller;

import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.model.Pacijent;
import hr.medick.medickapp.model.Podsjetnik;
import hr.medick.medickapp.model.Terapija;
import hr.medick.medickapp.service.PacijentService;
import hr.medick.medickapp.service.PodsjetnikService;
import hr.medick.medickapp.service.TerapijaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mobileReminders")
public class MobileReminderController {

    private final PacijentService pacijentService;
    private final TerapijaService terapijaService;
    private final PodsjetnikService podsjetnikService;

    public MobileReminderController(PacijentService pacijentService, TerapijaService terapijaService, PodsjetnikService podsjetnikService) {
        this.pacijentService = pacijentService;
        this.terapijaService = terapijaService;
        this.podsjetnikService = podsjetnikService;
    }

    @PostMapping()
    public ResponseEntity <List<Podsjetnik>> getAllRemindersForPacijent(@RequestBody Osoba osoba) {

        Pacijent pacijent = getPacijentByOsoba(osoba);

        List<Terapija> terapijaList = getTerapijaListByPacijent(pacijent);

        List<Podsjetnik> podjsetnikList = new ArrayList<>();

        for (Terapija terapija : terapijaList){
            podjsetnikList.add(getPodsjetnik(terapija));
        }

        return new ResponseEntity<>(podjsetnikList, HttpStatus.OK);
    }

    private Podsjetnik getPodsjetnik(Terapija terapija) {
        return podsjetnikService.getReminderByTerapija(terapija);
    }

    private List<Terapija> getTerapijaListByPacijent(Pacijent pacijent) {
        return terapijaService.getTerapijaListById(pacijent.getId());
    }

    private Pacijent getPacijentByOsoba(Osoba osoba) {
        return pacijentService.getPacijentById(osoba.getId());
    }
}

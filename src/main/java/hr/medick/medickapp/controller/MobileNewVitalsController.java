package hr.medick.medickapp.controller;

import hr.medick.medickapp.model.Pacijent;
import hr.medick.medickapp.model.Vitali;
import hr.medick.medickapp.service.PacijentService;
import hr.medick.medickapp.service.VitaliService;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/mobileSaveNewVitals")
public class MobileNewVitalsController {

    boolean secondTimeSaveSameVital = false;
    private final PacijentService pacijentService;
    private final VitaliService vitaliService;
    private Vitali newVital = new Vitali();

    public MobileNewVitalsController(PacijentService pacijentService, VitaliService vitaliService) {
        this.pacijentService = pacijentService;
        this.vitaliService = vitaliService;
    }


    @SneakyThrows
    @PostMapping()
    public ResponseEntity<Vitali> saveNewReminder(
            @RequestParam("glukozaukrvi") String glukozaukrvi,
            @RequestParam("krvnitlak") String krvnitlak,
            @RequestParam("osobaPacijentId") String osobaPacijentId) {

        if (!secondTimeSaveSameVital) {

            Pacijent pacijent = getPacijentFromId(osobaPacijentId);

            newVital.setPacijent(pacijent);
            newVital.setGlukozaukrvi(glukozaukrvi);
            newVital.setKrvnitlak(krvnitlak);
            newVital.setDatummjerenja(new Date());

            vitaliService.saveVitals(newVital);

            secondTimeSaveSameVital = true;

            return new ResponseEntity<>(newVital, HttpStatus.OK);
        }
        secondTimeSaveSameVital = false;

        return new ResponseEntity<>(newVital, HttpStatus.OK);

    }

    private Pacijent getPacijentFromId(String osobaPacijentId) {
        return pacijentService.getPacijentById(Long.valueOf(osobaPacijentId));
    }
}

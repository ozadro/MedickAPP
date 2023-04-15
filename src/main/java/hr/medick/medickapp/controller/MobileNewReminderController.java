package hr.medick.medickapp.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hr.medick.medickapp.model.*;
import hr.medick.medickapp.service.LijekService;
import hr.medick.medickapp.service.PacijentService;
import hr.medick.medickapp.service.PodsjetnikService;
import hr.medick.medickapp.service.TerapijaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ObjectStreamClass;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/mobileSaveNewReminder")
public class MobileNewReminderController {

    private final LijekService lijekService;
    private final TerapijaService terapijaService;
    private final PacijentService pacijentService;

    private final PodsjetnikService podsjetnikService;

    public MobileNewReminderController(LijekService lijekService, TerapijaService terapijaService, PacijentService pacijentService, PodsjetnikService podsjetnikService) {
        this.lijekService = lijekService;
        this.terapijaService = terapijaService;
        this.pacijentService = pacijentService;
        this.podsjetnikService = podsjetnikService;
    }

    @PostMapping()
    public ResponseEntity<Podsjetnik> saveNewReminder(
            @RequestParam("imeLijeka") String imeLijeka,
            @RequestParam("dozaLijeka") String dozaLijeka,
            @RequestParam("putaDnevno") String putaDnevno,
            @RequestParam("tableta") String tableta,
            @RequestParam("satiRazmaka") String satiRazmaka,
            @RequestParam("datumPrvogUzimanja") String datumPrvogUzimanja,
            @RequestParam("osobaPacijentId") String osobaPacijentId
    ) throws ParseException, JsonProcessingException {

        saveNewLijek(imeLijeka);

        Pacijent pacijent = getPacijentFromId(osobaPacijentId);

        Lijek lijek = getLijekFromName(imeLijeka);

        Terapija newTerapija = saveNewTerapija(lijek, pacijent, dozaLijeka, satiRazmaka, putaDnevno, tableta, datumPrvogUzimanja);

        Podsjetnik podsjetnik = new Podsjetnik();

        podsjetnik.setTerapija(newTerapija);
        podsjetnik.setUzet(false);

        podsjetnikService.savePodsjetnik(podsjetnik);

        return new ResponseEntity<>(podsjetnik, HttpStatus.OK);

    }

    private Lijek getLijekFromName(String lijek) {
        return lijekService.getLijekByName(lijek);
    }

    private Pacijent getPacijentFromId(String osobaPacijentId) {
        return pacijentService.getPacijentById(Long.valueOf(osobaPacijentId));
    }

    private Terapija saveNewTerapija(Lijek lijeka, Pacijent pacijent, String dozaLijeka, String satiRazmaka, String putaDnevno,
                                     String tableta,
                                     String datumPrvogUzimanja) throws ParseException, JsonProcessingException {

        Terapija terapija = new Terapija();
        terapija.setLijek(lijeka);
        terapija.setPacijent(pacijent);
        terapija.setDozalijeka(dozaLijeka);
        terapija.setPonavljanja(Float.parseFloat(satiRazmaka));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date datumPrvogUzimanjaFormatiran = dateFormat.parse(datumPrvogUzimanja);
        terapija.setPrvadoza(datumPrvogUzimanjaFormatiran);

        terapija.setKolicinatableta(Integer.parseInt(tableta));
        terapija.setKolicinadnevno(Integer.parseInt(putaDnevno));

        terapijaService.saveTerapija(terapija);

        return terapija;
    }

    private void saveNewLijek(String imeLijeka) {
        Lijek newLijek = new Lijek();
        if (!lijekService.existsLijekByName(imeLijeka)) {
            newLijek.setNaziv(imeLijeka);
            lijekService.saveLijek(newLijek);
        }
    }
}

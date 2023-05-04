package hr.medick.medickapp.controller;


import hr.medick.medickapp.model.Lijek;
import hr.medick.medickapp.model.Pacijent;
import hr.medick.medickapp.model.Podsjetnik;
import hr.medick.medickapp.model.Terapija;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/mobileSaveNewReminder")
public class MobileNewReminderController {
    boolean secondTimeSaveSamePacijent = false;
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
    ) throws ParseException {
        Podsjetnik podsjetnik = new Podsjetnik();

        if (!secondTimeSaveSamePacijent) {

            saveNewLijek(imeLijeka);

            Pacijent pacijent = getPacijentFromId(osobaPacijentId);

            Lijek lijek = getLijekFromName(imeLijeka);

            Terapija newTerapija = saveNewTerapija(lijek, pacijent, dozaLijeka, satiRazmaka, putaDnevno, tableta, datumPrvogUzimanja);

            podsjetnik.setTerapija(newTerapija);
            podsjetnik.setUzet(false);

            podsjetnikService.savePodsjetnik(podsjetnik);

            secondTimeSaveSamePacijent = true;

            return new ResponseEntity<>(podsjetnik, HttpStatus.OK);
        }

        secondTimeSaveSamePacijent = false;

        return new ResponseEntity<>(podsjetnik, HttpStatus.OK);

    }

    private Lijek getLijekFromName(String lijek) {
        return lijekService.getLijekByName(lijek);
    }

    private Pacijent getPacijentFromId(String osobaPacijentId) {
        return pacijentService.getPacijentById(Long.valueOf(osobaPacijentId));
    }

    private Terapija saveNewTerapija(Lijek lijeka, Pacijent pacijent,
                                     String dozaLijeka,
                                     String satiRazmaka,
                                     String putaDnevno,
                                     String tableta,
                                     String datumPrvogUzimanja) throws ParseException {

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

package hr.medick.medickapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class addPacijentController {

    @GetMapping("/addPatient.html")
    public String addIndex(){return "addPatient";}
}

package hr.medick.medickapp.controller;

import com.sun.security.auth.UserPrincipal;
import hr.medick.medickapp.model.Skrbnik;
import hr.medick.medickapp.service.SkrbnikService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Security;

@Controller
@SessionAttributes("email")
public class ViewSkrbnikController {

    private final SkrbnikService skrbnikService;
//    private final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    private final String currentPrincipalName = authentication.getName();

    public ViewSkrbnikController(SkrbnikService skrbnikService) {
        this.skrbnikService = skrbnikService;
    }

    @GetMapping("/viewSkrbnik")
    public String viewSkrbnik(Model model) {

        if (!model.containsAttribute("skrbnik")) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            Skrbnik skrbnik = skrbnikService.getSkrbnikByEmail(currentPrincipalName);

            model.addAttribute("skrbnik", skrbnik.getOsoba());
        }

        return "viewSkrbnik";
    }

}

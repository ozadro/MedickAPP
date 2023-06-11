package hr.medick.medickapp.security;

import hr.medick.medickapp.model.CustomUserDetails;
import hr.medick.medickapp.model.Osoba;
import hr.medick.medickapp.service.SkrbnikService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailAuthenticationProvider implements AuthenticationProvider {

    private SkrbnikService skrbnikService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        Osoba osoba = null;
        try {
            osoba = skrbnikService.getSkrbnikByEmail(email).getOsoba();
        } catch (NullPointerException nullPointerException) {
            throw new UsernameNotFoundException("Pogresna e mail adresa");
        }
        // Load user details by email
        CustomUserDetails userDetails = new CustomUserDetails(osoba);
//        if (osoba != null) {
//            userDetails = new CustomUserDetails(osoba);
//        } else {
//            throw new UsernameNotFoundException("Pogresna e mail adresa");
//        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean passwordMatches = passwordEncoder.matches(password, userDetails.getPassword());
        if (!passwordMatches) {
            throw new UsernameNotFoundException("Neispravna lozinka");
        }
        // Perform additional authentication checks if needed (e.g., password verification)

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}

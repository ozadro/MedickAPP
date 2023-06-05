package hr.medick.medickapp.security;

import hr.medick.medickapp.model.CustomUserDetails;
import hr.medick.medickapp.service.SkrbnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class EmailAuthenticationProvider implements AuthenticationProvider {

    private final SkrbnikService skrbnikService;

    @Autowired
    public EmailAuthenticationProvider(SkrbnikService skrbnikService) {
        this.skrbnikService = skrbnikService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Load user details by email
        UserDetails userDetails = new CustomUserDetails(skrbnikService.getSkrbnikByEmail(email).getOsoba());

        // Perform additional authentication checks if needed (e.g., password verification)

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

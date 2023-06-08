package hr.medick.medickapp.security;

import hr.medick.medickapp.error.CustomAuthenticationFailureHandler;
import hr.medick.medickapp.model.CustomUserDetails;
import hr.medick.medickapp.service.SkrbnikService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final EmailAuthenticationProvider emailAuthenticationProvider;
//    private final CustomUserDetails customUserDetails;
//    private final SkrbnikService skrbnikService;
//    @Bean
//    public UserDetailsManager users(DataSource dataSource) {
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        skrbnikService.getAllSkrbnik().forEach(s -> {
//            UserDetails user = User.withUsername(s.getOsoba().getEmail())
//                    .password(s.getOsoba().getLozinka())
//                    .build();
//            users.createUser(user);
//        });
//
//        return users;
//    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(emailAuthenticationProvider);

        return authenticationManagerBuilder.build();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http/*, AuthenticationManager authManager*/) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(  "/resources/**",
                                "/static/**", "/webjars/**", "/login",
                                "/register", "/css/**", "/js/**",
                                "/mobileRegister","/mobileReminders","/mobileVitals","/mobileVitals",
                                "/mobileSaveNewVitals","/mobileSaveNewReminder","/mobileLogin","/mobileEditPacijent")
                        .permitAll()
                        .anyRequest().authenticated()
                )//.authenticationManager(authManager)
                .formLogin((form) -> form
                        .loginPage("/login")
                        .usernameParameter("email")
                        .defaultSuccessUrl("/searchPatient")
                        .failureUrl("/login-error")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
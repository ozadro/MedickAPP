package hr.medick.medickapp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/viewPatient").setViewName("viewPatient");
        registry.addViewController("/viewSkrbnik").setViewName("viewSkrbnik");
        registry.addViewController("/searchPatient").setViewName("searchPatient");
        registry.addViewController("/").setViewName("searchPatient");
    }

}
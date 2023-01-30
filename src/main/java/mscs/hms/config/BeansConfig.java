package mscs.hms.config;

import mscs.hms.security.CustomUserDetails;
import mscs.hms.security.CustomUserDetailsService;
import mscs.hms.services.IUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeansConfig {

    @Bean
    protected UserDetailsService getUserDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    protected BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

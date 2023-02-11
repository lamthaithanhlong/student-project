package mscs.hms.config;

import mscs.hms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final String[] SWAGGER_AUTH_WHITELIST = {
            "/authenticate",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/swagger-ui/index.html",
            "/v2/api-docs",
            //"/webjars/**",
            "/swagger-ui.html"
    };

    private static final String[] APP_URL_TO_WHITELIST = {
            "/", "/index", "/home",
            "/process_register", "/register", "/register_success",
            "/webjars/**",
            "/properties-list",
            "/legal-entities",
            "/addresses",
            "/login",
            "/logout",
            "/error"
    };

    private static final String[] RENTER_ACCESS_URLS = {
            "/companies",
            "/persons", 
            "/houses", 
            "/apartments",
            "/inquiries", "/inquiry_new", "/inquiry_edit/**", "/inquiry/new", "/inquiry/edit", "/inquiry/edit",
            "/rent_applications",  "/rentapplication_new", "/rentapplication_edit/**",  "/rentapplication/new", "/rentapplication/edit",
            "/rental_agreements",  "/rentalagreement_edit/**", "/rentalagreement/edit"
    };

    private static final String[] OWNER_ACCESS_URLS = {
            "/companies",
            "/addresses", "/address_new", "/address_edit/**",  "/address/new", "/address/edit",                      
            "/persons",
            "/houses", "/house_new", "/house_edit/**", "/house/new", "/house/edit",
            "/apartments", "/apartment_new", "/apartment_edit/**", "/apartment/new", "/apartment/edit",
            "/inquiries", "/inquiry_new", "/inquiry_edit/**", "/inquiry/new", "/inquiry/edit",
            "/rent_applications", "/rentapplication_edit/**", "/rentapplication/edit",
            "/rental_agreements", "/rentalagreement_new", "/rentalagreement_edit/**", "/rentalagreement/new", "/rentalagreement/edit",
    };

    private static final String[] RESOURCES_TO_WHITELIST = {
            "/resources/**",
            "/css/**",
            "/js/**",
            "/images/**"
    };



    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder);

        return authProvider;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().and().cors().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(APP_URL_TO_WHITELIST).permitAll()
                        .requestMatchers(RENTER_ACCESS_URLS).hasAnyAuthority("Admin", "Renter", "Owner")
                        .requestMatchers(OWNER_ACCESS_URLS).hasAnyAuthority("Admin", "Owner")
                        .requestMatchers("/**").hasAuthority("Admin")
                        .requestMatchers(SWAGGER_AUTH_WHITELIST).hasAuthority("Admin")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form.loginPage("/login")
                                        .failureUrl("/login?error")
                                        .defaultSuccessUrl("/home", true)
                                        .permitAll())
                .logout((logout) -> logout.invalidateHttpSession(true)
                                        .deleteCookies("JSESSIONID")
                                        .logoutSuccessUrl("/login?logout")
                                        .permitAll()
                                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")));

        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(RESOURCES_TO_WHITELIST);
    }
}

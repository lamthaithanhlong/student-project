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

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private AuthenticationHandler authenticationHandler;

    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final String[] SWAGGER_AUTH_WHITELIST = {
            "/authenticate",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v2/api-docs",
            //"/webjars/**",
            "/swagger-ui.html"
    };

    private static final String[] APP_URL_TO_WHITELIST = {
            "/", "/index",
            "/process_register", "/register", "/register_success",
            "/webjars/**"
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
                        //.requestMatchers(SWAGGER_AUTH_WHITELIST).permitAll()
                        .anyRequest().authenticated()
                )
                //.httpBasic(Customizer.withDefaults())
                .formLogin((form) -> form.loginPage("/login")
                                        //.successHandler(authenticationHandler)
                                        .failureUrl("/login?error")
                                        .defaultSuccessUrl("/home", true)
                                        .permitAll())
                .logout((logout) -> logout.logoutSuccessUrl("/login?logout")
                .permitAll());

        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(RESOURCES_TO_WHITELIST);
    }
}

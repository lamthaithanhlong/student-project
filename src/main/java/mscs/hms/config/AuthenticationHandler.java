package mscs.hms.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mscs.hms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class AuthenticationHandler implements AuthenticationSuccessHandler {

	@Autowired
	private UserRepository userRepository;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for(GrantedAuthority authority : authorities) {
			try {
				if(authority.getAuthority().equals(userRepository.getRoleByName("Admin"))) {
					redirectStrategy.sendRedirect(request, response, "/home");
				} else if(authority.getAuthority().equals(userRepository.getRoleByName("Owner"))) {
					redirectStrategy.sendRedirect(request, response, "/home");
				} else if(authority.getAuthority().equals(userRepository.getRoleByName("Renter"))) {
					redirectStrategy.sendRedirect(request, response, "/home");
				} /*else {
					redirectStrategy.sendRedirect(request, response, "/access-denied");
		        }*/
			} catch (Exception e) {
				//TODO: Add logger here
				e.printStackTrace();
			}
		}
	}
}

package mscs.hms.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationHandler implements AuthenticationSuccessHandler {

	//@Autowired
	//private UserRepository userRepository;

	//private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		/*Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for(GrantedAuthority authority : authorities) {
			try {
				if(authority.getAuthority().equals(userRepository.getRoleByName("Admin"))) {
					redirectStrategy.sendRedirect(request, response, "/home");
				} else if(authority.getAuthority().equals(userRepository.getRoleByName("Owner"))) {
					redirectStrategy.sendRedirect(request, response, "/home");
				} else if(authority.getAuthority().equals(userRepository.getRoleByName("Renter"))) {
					redirectStrategy.sendRedirect(request, response, "/home");
				} //else {
					//redirectStrategy.sendRedirect(request, response, "/access-denied");
		        //}
			} catch (Exception e) {
				//TODO: Add logger here
				e.printStackTrace();
			}
		}*/

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		System.out.println("The user " + username + " has logged in.");
		response.sendRedirect(request.getContextPath());
	}
}

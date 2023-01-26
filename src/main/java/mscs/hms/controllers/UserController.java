package mscs.hms.controllers;

import mscs.hms.models.Role;
import mscs.hms.models.UserInfo;
import mscs.hms.services.IUserService;
import mscs.hms.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDetailsServiceImpl userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserInfo());
        return "signup";
    }

    @PostMapping("/process_register")
    public String processRegister(UserInfo user) {
        System.out.println("Registration request received");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        //Adding Guest Role to user if no role selected
        if (user.getRoles().isEmpty()) {
            Role role = userService.getAllRoleByName("Guest");

            if (role != null) {
                user.getRoles().add(role);
            }
        }

        userService.saveUser(user);

        return "register_success";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<UserInfo> users = userService.findAllUsers();
        model.addAttribute("users", users);

        return "users";
    }
}

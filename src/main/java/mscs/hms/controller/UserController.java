package mscs.hms.controller;

import mscs.hms.model.Role;
import mscs.hms.model.User;
import mscs.hms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController extends AbsBaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        LOG.info("In register view");
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        LOG.info("Registration request received");
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        //Adding Guest Role to user if no role selected
        if (user.getRoles().isEmpty()) {
            Role role = userService.getRoleByName("Guest");

            if (role != null) {
                user.getRoles().add(role);
            }
        }

        userService.saveUser(user);

        return "register_success";
    }

    @GetMapping("/users")
    public String listUsers(Principal principal, Model model) {
        LOG.info("I am in user list view");
        List<User> users = userService.findAllUsers();
        for(User user : users) {
            LOG.info("Username = " + user.getUsername());
        }
        model.addAttribute("users", users);
        model.addAttribute("loggedInUserName", principal.getName());
        
        return "user_list";
    }
}

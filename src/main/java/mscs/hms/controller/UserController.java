package mscs.hms.controller;

import mscs.hms.entity.Role;
import mscs.hms.entity.User;
import mscs.hms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        System.out.println("Registration request received");
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
    public String listUsers(Model model) {
        List<User> users = userService.findAllUsers();
        for(User user : users) {
            System.out.println("Username = " + user.getUsername());
        }
        model.addAttribute("users", users);

        return "user_list";
    }
}

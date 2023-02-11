package mscs.hms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController extends AbsBaseController {

    @GetMapping({"/", "/index"})
    public String index() {
        LOG.info("In index view");
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        LOG.info("In home view");
        return "home";
    }

    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("errorMsg", "Your username and password are invalid.");
        }

        if (logout != null) {
            model.addAttribute("msg", "You have been logged out successfully.");
        }

        return "login";
    }
}

package mscs.hms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController extends AbsBaseController {

    @GetMapping({"/", "/index"})
    public String index() {
        LOG.info("In index view");
        return "index";
    }

    @GetMapping("/home")
    public String home(Principal principal, Model model) {
        LOG.info("In home view");
        model.addAttribute("loggedInUserName", principal.getName());
        return "home";
    }

    @Override
    protected void addViewGenerationProperties(ModelAndView modelAndView) {}
}

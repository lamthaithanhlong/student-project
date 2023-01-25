package mscs.hms.controllers;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ComponentScan(basePackages = {"mscs.hms"})
public class AppBaseController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }
}

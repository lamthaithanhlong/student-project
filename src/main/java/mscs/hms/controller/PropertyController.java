package mscs.hms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PropertyController extends AbsBaseController {

    @GetMapping("/property_list")
    public String properties() {
        LOG.info("In Property List view");
        return "property_list";
    }
}

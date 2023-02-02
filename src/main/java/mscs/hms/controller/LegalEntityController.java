package mscs.hms.controller;

import mscs.hms.entity.LegalEntity;
import mscs.hms.service.LegalEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LegalEntityController extends AbsEntityController {
    
    @Autowired
    private LegalEntityService legalentityService;

    @GetMapping("/legal-entities")
    public ModelAndView showCompanies(Model model) {
        LOG.info("In legalentities view");
        return getListEntitiesModelView(legalentityService.findAll());
    }

    @Override
    protected Class<?> getClassType(){
        return LegalEntity.class;
    }
    @Override
    protected String getEditViewPath(){
        return null;
    }
    @Override
    protected String getListViewPath(){
        return "/legal-entities";
    }
    @Override
    protected String getNewViewPath(){
        return null;
    }
    @Override
    protected String getCrudPath(){
        return null;
    }
}

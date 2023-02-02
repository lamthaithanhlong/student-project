package mscs.hms.controller;

import mscs.hms.entity.LegalEntity;
import mscs.hms.service.LegalEntityService;

import java.util.Dictionary;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LegalEntityController extends AbsEntityController<LegalEntity> {
    
    @Autowired
    private LegalEntityService legalentityService;

    @GetMapping("/legal-entities")
    public ModelAndView showCompanies(Model model) {
        LOG.info("In legalentities view");
        return getListEntitiesModelView(legalentityService.findAll());
    }

    @Override
    public Class<?> getClassType(){
        return LegalEntity.class;
    }
    @Override
    public String getEditViewPath(){
        return null;
    }
    @Override
    public String getListViewPath(){
        return "/legal-entities";
    }
    @Override
    public String getNewViewPath(){
        return null;
    }
    @Override
    public String getCrudPath(){
        return null;
    }
    @Override
    public Dictionary<String, Iterable<?>> getSelectLists(){
        Dictionary<String, Iterable<?>> dictionary = new Hashtable<>();
        return dictionary;
    }
}

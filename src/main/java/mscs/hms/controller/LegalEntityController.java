package mscs.hms.controller;

import mscs.hms.model.LegalEntity;
import mscs.hms.service.LegalEntityService;
import mscs.hms.service.IUserService;
import mscs.hms.service.AddressService;
import mscs.hms.dto.selectors.UserSelectorDTO;
import mscs.hms.dto.selectors.AddressSelectorDTO;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LegalEntityController extends AbsEntityController<LegalEntity> {
    
    @Autowired
    private LegalEntityService legalentityService;

    @Autowired
    private IUserService userService;

    @Autowired
    private AddressService addressService;

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
        return "/legal_entity_list";
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
    public Dictionary<String, List<?>> getSelectLists(){
        Dictionary<String, List<?>> dictionary = new Hashtable<>();
        //Note used same attributeName "systemUser"
        dictionary.put("systemUser", userService.findAllUsers().stream().map(UserSelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("address", addressService.findAll().stream().map(AddressSelectorDTO::new).collect(Collectors.toList()));
        return dictionary;
    }
}

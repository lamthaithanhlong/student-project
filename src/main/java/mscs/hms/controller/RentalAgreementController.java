package mscs.hms.controller;

import mscs.hms.model.RentalAgreement;
import mscs.hms.service.AddressService;
import mscs.hms.service.RentalAgreementService;
import mscs.hms.dto.selectors.UserSelectorDTO;
import mscs.hms.dto.selectors.AddressSelectorDTO;
import mscs.hms.service.IUserService;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RentalAgreementController extends AbsEntityController<RentalAgreement> {
    
    @Autowired
    private RentalAgreementService rentalagreementService;

    @Autowired
    private IUserService userService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/rental_agreements")
    public ModelAndView showCompanies(Model model) {
        LOG.info("In inquiries view");
        return getListEntitiesModelView(rentalagreementService.findAll());
    }    

    @GetMapping("/rentalagreement_new")
    public ModelAndView newRentalAgreementForm() {
        LOG.info("In inquiries new");
        ModelAndView modelAndView = getEditViewModel(new RentalAgreement(), "new");
        return modelAndView;
    }    

    @GetMapping("/rentalagreement_edit/{id}")
    public ModelAndView editRentalAgreementForm(@PathVariable(value="id") final Integer rentalagreementId) {
        LOG.info("In inquiries edit");
        return getEditViewModel(rentalagreementService.getById(rentalagreementId), "edit");        
    }

    @PostMapping("/rentalagreement/delete") 
    public ModelAndView requestOTP( @RequestParam(value="id") Integer id) {
        LOG.info("In inquiries delete");
        rentalagreementService.deleteById(id);
        return getListEntitiesModelView(rentalagreementService.findAll());
    }

    @PostMapping("/rentalagreement/edit")
    public ModelAndView processEdit(RentalAgreement rentalagreement) {
        LOG.info("In inquiries edit");
        try{
            rentalagreementService.save(rentalagreement);
        }
        catch(Exception ex){
            return getEditViewModel(rentalagreement, getObjectErrorList(ex), "edit");
        }
        return getListEntitiesModelView(rentalagreementService.findAll());
    }

    @PostMapping("/rentalagreement/new")
    public ModelAndView processNew(RentalAgreement rentalagreement) {
        LOG.info("In inquiries new");
        try{
            rentalagreementService.save(rentalagreement);
        }
        catch(Exception ex){
            return getEditViewModel(rentalagreement, getObjectErrorList(ex), "edit");
        }
        return getListEntitiesModelView(rentalagreementService.findAll());
    } 
    
    @Override
    public Class<?> getClassType(){
        return RentalAgreement.class;
    }
    @Override
    public String getEditViewPath(){
        return "/rentalagreement_edit";
    }
    @Override
    public String getListViewPath(){
        return "/rentalagreement_list";
    }
    @Override
    public String getNewViewPath(){
        return "/rentalagreement_new";
    }
    @Override
    public String getCrudPath(){
        return "/rentalagreement";
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

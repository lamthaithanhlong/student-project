package mscs.hms.controller;

import mscs.hms.model.RentApplication;
import mscs.hms.service.AddressService;
import mscs.hms.service.RentApplicationService;
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
public class RentApplicationController extends AbsEntityController<RentApplication> {
    
    @Autowired
    private RentApplicationService rentapplicationService;

    @Autowired
    private IUserService userService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/rent_applications")
    public ModelAndView showCompanies(Model model) {
        LOG.info("In inquiries view");
        return getListEntitiesModelView(rentapplicationService.findAll());
    }    

    @GetMapping("/rentapplication_new")
    public ModelAndView newRentApplicationForm() {
        LOG.info("In inquiries new");
        ModelAndView modelAndView = getEditViewModel(new RentApplication(), "new");
        return modelAndView;
    }    

    @GetMapping("/rentapplication_edit/{id}")
    public ModelAndView editRentApplicationForm(@PathVariable(value="id") final Integer rentapplicationId) {
        LOG.info("In inquiries edit");
        return getEditViewModel(rentapplicationService.getById(rentapplicationId), "edit");        
    }

    @PostMapping("/rentapplication/delete") 
    public ModelAndView requestOTP( @RequestParam(value="id") Integer id) {
        LOG.info("In inquiries delete");
        rentapplicationService.deleteById(id);
        return getListEntitiesModelView(rentapplicationService.findAll());
    }

    @PostMapping("/rentapplication/edit")
    public ModelAndView processEdit(RentApplication rentapplication) {
        LOG.info("In inquiries edit");
        try{
            rentapplicationService.save(rentapplication);
        }
        catch(Exception ex){
            return getEditViewModel(rentapplication, getObjectErrorList(ex), "edit");
        }
        return getListEntitiesModelView(rentapplicationService.findAll());
    }

    @PostMapping("/rentapplication/new")
    public ModelAndView processNew(RentApplication rentapplication) {
        LOG.info("In inquiries new");
        try{
            rentapplicationService.save(rentapplication);
        }
        catch(Exception ex){
            return getEditViewModel(rentapplication, getObjectErrorList(ex), "edit");
        }
        return getListEntitiesModelView(rentapplicationService.findAll());
    } 
    
    @Override
    public Class<?> getClassType(){
        return RentApplication.class;
    }
    @Override
    public String getEditViewPath(){
        return "/rentapplication_edit";
    }
    @Override
    public String getListViewPath(){
        return "/rentapplication_list";
    }
    @Override
    public String getNewViewPath(){
        return "/rentapplication_new";
    }
    @Override
    public String getCrudPath(){
        return "/rentapplication";
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

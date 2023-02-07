package mscs.hms.controller;

import mscs.hms.model.Landlord;
import mscs.hms.service.LandlordService;
import mscs.hms.dto.selectors.UserSelectorDTO;
import mscs.hms.dto.selectors.InquirySelectorDTO;
import mscs.hms.dto.selectors.LegalEntitySelectorDTO;
import mscs.hms.dto.selectors.PropertySelectorDTO;
import mscs.hms.dto.selectors.PreferenceSelectorDTO;
import mscs.hms.dto.selectors.RentApplicationSelectorDTO;
import mscs.hms.dto.selectors.RentalAgreementSelectorDTO;
import mscs.hms.service.IUserService;
import mscs.hms.service.InquiryService;
import mscs.hms.service.PropertyService;
import mscs.hms.service.PreferenceService;
import mscs.hms.service.RentalAgreementService;
import mscs.hms.service.RentApplicationService;
import mscs.hms.service.LegalEntityService;
import mscs.hms.controller.editors.LegalEntityEditor;
import mscs.hms.controller.editors.InquiryEditor;
import mscs.hms.controller.editors.RentalAgreementEditor;
import mscs.hms.controller.editors.RentApplicationEditor;
import mscs.hms.controller.editors.PropertyEditor;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LandlordController extends AbsEntityController<Landlord> {
    
    @Autowired
    private LandlordService landlordService;

    @Autowired
    private IUserService userService;

    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private LegalEntityService legalEntityService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private RentApplicationService rentApplicationService;

    @Autowired
    private RentalAgreementService rentalAgreementService;

    @InitBinder
    public void customizeBinding (WebDataBinder binder) {
        binder.registerCustomEditor(String[].class, "legalEntity", 
                                    new LegalEntityEditor(legalEntityService, true));
        binder.registerCustomEditor(List.class, "properties", 
                                    new PropertyEditor(propertyService, true));        
    }

    @GetMapping("/landlords")
    public ModelAndView showCompanies(Model model) {
        LOG.info("In landlords view");
        return getListEntitiesModelView(landlordService.findAll());
    }    

    @GetMapping("/landlord_new")
    public ModelAndView newLandlordForm() {
        LOG.info("In landlords new");
        ModelAndView modelAndView = getEditViewModel(new Landlord(), "new");
        return modelAndView;
    }    

    @GetMapping("/landlord_edit/{id}")
    public ModelAndView editLandlordForm(@PathVariable(value="id") final Integer landlordId) {
        LOG.info("In landlords edit");
        return getEditViewModel(landlordService.getById(landlordId), "edit");        
    }

    @PostMapping("/landlord/delete") 
    public ModelAndView requestOTP( @RequestParam(value="id") Integer id) {
        LOG.info("In landlords delete");
        landlordService.deleteById(id);
        return getListEntitiesModelView(landlordService.findAll());
    }

    @PostMapping("/landlord/edit")
    public ModelAndView processEdit(Landlord landlord) {
        LOG.info("In landlords edit");
        try{
            landlordService.save(landlord);
        }
        catch(Exception ex){
            return getEditViewModel(landlord, getObjectErrorList(ex), "edit");
        }
        return getListEntitiesModelView(landlordService.findAll());
    }

    @PostMapping("/landlord/new")
    public ModelAndView processNew(Landlord landlord) {
        LOG.info("In landlords new");
        try{
            landlordService.save(landlord);
        }
        catch(Exception ex){
            return getEditViewModel(landlord, getObjectErrorList(ex), "edit");
        }
        return getListEntitiesModelView(landlordService.findAll());
    } 
    
    @Override
    public Class<?> getClassType(){
        return Landlord.class;
    }
    @Override
    public String getEditViewPath(){
        return "/landlord_edit";
    }
    @Override
    public String getListViewPath(){
        return "/landlord_list";
    }
    @Override
    public String getNewViewPath(){
        return "/landlord_new";
    }
    @Override
    public String getCrudPath(){
        return "/landlord";
    }
    @Override
    public Dictionary<String, List<?>> getSelectLists(){
        Dictionary<String, List<?>> dictionary = new Hashtable<>();
        //Note used same attributeName "systemUser"
        dictionary.put("systemUser", userService.findAllUsers().stream().map(UserSelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("inquiries", inquiryService.findAll().stream().map(InquirySelectorDTO::new).collect(Collectors.toList()));        
        dictionary.put("properties", propertyService.getProperties().stream().map(PropertySelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("rentApplications", rentApplicationService.findAll().stream().map(RentApplicationSelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("rentalAgreements", rentalAgreementService.findAll().stream().map(RentalAgreementSelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("legalEntity", legalEntityService.findAll().stream().map(LegalEntitySelectorDTO::new).collect(Collectors.toList()));
        return dictionary;
    }
}

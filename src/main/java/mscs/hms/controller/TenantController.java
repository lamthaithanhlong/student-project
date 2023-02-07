package mscs.hms.controller;

import mscs.hms.model.Tenant;
import mscs.hms.service.TenantService;
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
public class TenantController extends AbsEntityController<Tenant> {
    
    @Autowired
    private TenantService tenantService;

    @Autowired
    private IUserService userService;

    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private LegalEntityService legalEntityService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PreferenceService preferenceService;

    @Autowired
    private RentApplicationService rentApplicationService;

    @Autowired
    private RentalAgreementService rentalAgreementService;

    @InitBinder
    public void customizeBinding (WebDataBinder binder) {
        binder.registerCustomEditor(String[].class, "legalEntity", 
                                    new LegalEntityEditor(legalEntityService, true));
        binder.registerCustomEditor(List.class, "inquiries", 
                                    new InquiryEditor(inquiryService, true));
        binder.registerCustomEditor(List.class, "properties", 
                                    new PropertyEditor(propertyService, true));         
        binder.registerCustomEditor(List.class, "rentalAgreements", 
                                    new RentalAgreementEditor(rentalAgreementService, true));                                             
        binder.registerCustomEditor(List.class, "rentApplications", 
                                    new RentApplicationEditor(rentApplicationService, true));
    }

    @GetMapping("/tenants")
    public ModelAndView showCompanies(Model model) {
        LOG.info("In tenants view");
        return getListEntitiesModelView(tenantService.findAll());
    }    

    @GetMapping("/tenant_new")
    public ModelAndView newTenantForm() {
        LOG.info("In tenants new");
        ModelAndView modelAndView = getEditViewModel(new Tenant(), "new");
        return modelAndView;
    }    

    @GetMapping("/tenant_edit/{id}")
    public ModelAndView editTenantForm(@PathVariable(value="id") final Integer tenantId) {
        LOG.info("In tenants edit");
        return getEditViewModel(tenantService.getById(tenantId), "edit");        
    }

    @PostMapping("/tenant/delete") 
    public ModelAndView requestOTP( @RequestParam(value="id") Integer id) {
        LOG.info("In tenants delete");
        tenantService.deleteById(id);
        return getListEntitiesModelView(tenantService.findAll());
    }

    @PostMapping("/tenant/edit")
    public ModelAndView processEdit(Tenant tenant) {
        LOG.info("In tenants edit");
        try{
            tenantService.save(tenant);
        }
        catch(Exception ex){
            return getEditViewModel(tenant, getObjectErrorList(ex), "edit");
        }
        return getListEntitiesModelView(tenantService.findAll());
    }

    @PostMapping("/tenant/new")
    public ModelAndView processNew(Tenant tenant) {
        LOG.info("In tenants new");
        try{
            tenantService.save(tenant);
        }
        catch(Exception ex){
            return getEditViewModel(tenant, getObjectErrorList(ex), "edit");
        }
        return getListEntitiesModelView(tenantService.findAll());
    } 
    
    @Override
    public Class<?> getClassType(){
        return Tenant.class;
    }
    @Override
    public String getEditViewPath(){
        return "/tenant_edit";
    }
    @Override
    public String getListViewPath(){
        return "/tenant_list";
    }
    @Override
    public String getNewViewPath(){
        return "/tenant_new";
    }
    @Override
    public String getCrudPath(){
        return "/tenant";
    }
    @Override
    public Dictionary<String, List<?>> getSelectLists(){
        Dictionary<String, List<?>> dictionary = new Hashtable<>();
        //Note used same attributeName "systemUser"
        dictionary.put("systemUser", userService.findAllUsers().stream().map(UserSelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("inquiries", inquiryService.findAll().stream().map(InquirySelectorDTO::new).collect(Collectors.toList()));        
        dictionary.put("properties", propertyService.getProperties().stream().map(PropertySelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("rentApplications", rentApplicationService.findAll().stream().map(RentApplicationSelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("preference", preferenceService.findAll().stream().map(PreferenceSelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("rentalAgreements", rentalAgreementService.findAll().stream().map(RentalAgreementSelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("legalEntity", legalEntityService.findAll().stream().map(LegalEntitySelectorDTO::new).collect(Collectors.toList()));
        return dictionary;
    }
}

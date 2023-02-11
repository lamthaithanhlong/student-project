package mscs.hms.controller;

import mscs.hms.controller.editors.PropertyEditor;
import mscs.hms.dto.selectors.*;
import mscs.hms.model.Property;
import mscs.hms.model.RentalAgreement;
import mscs.hms.service.*;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RentalAgreementController extends AbsEntityController<RentalAgreement> {
    
    @Autowired
    private RentalAgreementService rentalagreementService;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private LandlordService landlordService;

    @Autowired
    private PropertyService propertyService;

    @InitBinder
    public void customizeBinding (WebDataBinder binder) {
        binder.registerCustomEditor(Property.class, "property",
                                    new PropertyEditor(propertyService, true));
    }
    
    @GetMapping("/rental_agreements")
    public ModelAndView showCompanies(Model model,
                                      @RequestParam("page") Optional<Integer> page,
                                      @RequestParam("size") Optional<Integer> size,
                                      @RequestParam("search") Optional<String> search) {
        LOG.info("In rental agreements view");
        int currentPage = getCurrentPage(page);
        int pageSize = getPageSize(size);
        String searchString = getSearchString(search);
        return getListEntitiesModelView(rentalagreementService.getAll(searchString, currentPage, pageSize));
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
        return getListEntitiesModelView(rentalagreementService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
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
        return getListEntitiesModelView(rentalagreementService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/rentalagreement/new")
    public ModelAndView processNew(RentalAgreement rentalagreement) {
        LOG.info("In inquiries new");
        try{
            rentalagreementService.save(rentalagreement);
        }
        catch(Exception ex){
            return getEditViewModel(rentalagreement, getObjectErrorList(ex), "new");
        }
        return getListEntitiesModelView(rentalagreementService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
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
    public String getListPath() { return "/rental_agreements";}
    @Override
    public Dictionary<String, List<?>> getSelectLists(){
        Dictionary<String, List<?>> dictionary = new Hashtable<>();
        //Note used same attributeName "systemUser"
        dictionary.put("tenant", tenantService.findAll().stream().map(TenantSelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("landlord", landlordService.findAll().stream().map(LandLordSelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("property", propertyService.getProperties().stream().map(PropertySelectorDTO::new).collect(Collectors.toList()));
        return dictionary;
    }
}

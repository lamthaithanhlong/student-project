package mscs.hms.controller;

import mscs.hms.controller.editors.PropertyEditor;
import mscs.hms.dto.selectors.*;
import mscs.hms.model.Property;
import mscs.hms.model.RentApplication;
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
public class RentApplicationController extends AbsEntityController<RentApplication> {
    
    @Autowired
    private RentApplicationService rentapplicationService;

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
    
    @GetMapping("/rent_applications")
    public ModelAndView showCompanies(Model model,
                                      @RequestParam("page") Optional<Integer> page,
                                      @RequestParam("size") Optional<Integer> size,
                                      @RequestParam("search") Optional<String> search) {
        LOG.info("In rental applications view");
        int currentPage = getCurrentPage(page);
        int pageSize = getPageSize(size);
        String searchString = getSearchString(search);
        return getListEntitiesModelView(rentapplicationService.getAll(searchString, currentPage, pageSize));
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
        return getListEntitiesModelView(rentapplicationService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
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
        return getListEntitiesModelView(rentapplicationService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/rentapplication/new")
    public ModelAndView processNew(RentApplication rentapplication) {
        LOG.info("In inquiries new");
        try{
            rentapplicationService.save(rentapplication);
        }
        catch(Exception ex){
            return getEditViewModel(rentapplication, getObjectErrorList(ex), "new");
        }
        return getListEntitiesModelView(rentapplicationService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
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
    public String getListPath() { return "/rent_applications";}
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

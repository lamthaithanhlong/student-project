package mscs.hms.controller;

import mscs.hms.controller.editors.PropertyEditor;
import mscs.hms.dto.selectors.*;
import mscs.hms.model.Inquiry;
import mscs.hms.model.Property;
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
public class InquiryController extends AbsEntityController<Inquiry> {
    
    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private IUserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private LandlordService landlordService;

    @InitBinder
    public void customizeBinding (WebDataBinder binder) {
        binder.registerCustomEditor(Property.class, "property",
                                    new PropertyEditor(propertyService, true));
    }
    
    @GetMapping("/inquiries")
    public ModelAndView showCompanies(Model model,
                                      @RequestParam("page") Optional<Integer> page,
                                      @RequestParam("size") Optional<Integer> size,
                                      @RequestParam("search") Optional<String> search) {
        LOG.info("In Companies view");
        int currentPage = getCurrentPage(page);
        int pageSize = getPageSize(size);
        String searchString = getSearchString(search);
        return getListEntitiesModelView(inquiryService.getAll(searchString, currentPage, pageSize));
    }    

    @GetMapping("/inquiry_new")
    public ModelAndView newInquiryForm() {
        LOG.info("In inquiries new");
        ModelAndView modelAndView = getEditViewModel(new Inquiry(), "new");
        return modelAndView;
    }    

    @GetMapping("/inquiry_edit/{id}")
    public ModelAndView editInquiryForm(@PathVariable(value="id") final Integer inquiryId) {
        LOG.info("In inquiries edit");
        return getEditViewModel(inquiryService.getById(inquiryId), "edit");        
    }

    @PostMapping("/inquiry/delete") 
    public ModelAndView requestOTP( @RequestParam(value="id") Integer id) {
        LOG.info("In inquiries delete");
        inquiryService.deleteById(id);
        return getListEntitiesModelView(inquiryService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/inquiry/edit")
    public ModelAndView processEdit(Inquiry inquiry) {
        LOG.info("In inquiries edit");
        try{
            inquiryService.save(inquiry);
        }
        catch(Exception ex){
            return getEditViewModel(inquiry, getObjectErrorList(ex), "edit");
        }
        return getListEntitiesModelView(inquiryService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/inquiry/new")
    public ModelAndView processNew(Inquiry inquiry) {
        LOG.info("In inquiries new");
        try{
            inquiryService.save(inquiry);
        }
        catch(Exception ex){
            return getEditViewModel(inquiry, getObjectErrorList(ex), "new");
        }
        return getListEntitiesModelView(inquiryService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    } 
    
    @Override
    public Class<?> getClassType(){
        return Inquiry.class;
    }
    @Override
    public String getEditViewPath(){
        return "/inquiry_edit";
    }
    @Override
    public String getListViewPath(){
        return "/inquiry_list";
    }
    @Override
    public String getNewViewPath(){
        return "/inquiry_new";
    }
    @Override
    public String getCrudPath(){
        return "/inquiry";
    }
    @Override
    public String getListPath() { return "/inquiries";}
    @Override
    public Dictionary<String, List<?>> getSelectLists(){
        Dictionary<String, List<?>> dictionary = new Hashtable<>();
        //Note used same attributeName "systemUser"
        dictionary.put("systemUser", userService.findAllUsers().stream().map(UserSelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("address", addressService.findAll().stream().map(AddressSelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("tenant", tenantService.findAll().stream().map(TenantSelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("landlord", landlordService.findAll().stream().map(LandLordSelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("property", propertyService.getProperties().stream().map(PropertySelectorDTO::new).collect(Collectors.toList()));
        return dictionary;
    }
}

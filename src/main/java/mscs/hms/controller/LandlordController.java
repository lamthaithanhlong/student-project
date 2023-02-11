package mscs.hms.controller;

import mscs.hms.model.Landlord;
import mscs.hms.model.Role;
import mscs.hms.model.User;
import mscs.hms.service.IUserService;
import mscs.hms.service.LandlordService;
import mscs.hms.dto.selectors.LegalEntitySelectorDTO;
import mscs.hms.dto.selectors.PropertySelectorDTO;
import mscs.hms.service.PropertyService;
import mscs.hms.service.LegalEntityService;
import mscs.hms.controller.editors.PropertyListEditor;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LandlordController extends AbsEntityController<Landlord> {

    @Autowired
    private IUserService userService;
    @Autowired
    private LandlordService landlordService;

    @Autowired
    private LegalEntityService legalEntityService;

    @Autowired
    private PropertyService propertyService;

    @InitBinder
    public void customizeBinding (WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "properties",
                                    new PropertyListEditor(propertyService, true));        
    }

    @GetMapping("/landlords")
    public ModelAndView showCompanies(Model model,
                                      @RequestParam("page") Optional<Integer> page,
                                      @RequestParam("size") Optional<Integer> size,
                                      @RequestParam("search") Optional<String> search) {
        LOG.info("In Landlords view");
        int currentPage = getCurrentPage(page);
        int pageSize = getPageSize(size);
        String searchString = getSearchString(search);
        return getListEntitiesModelView(landlordService.getAll(searchString, currentPage, pageSize));
    }    

    @GetMapping("/landlord_new")
    public ModelAndView newLandlordForm() {
        LOG.info("In landlords new");
        return getEditViewModel(new Landlord(), "new");
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
        return getListEntitiesModelView(landlordService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/landlord/edit")
    public ModelAndView processEdit(Landlord landlord) {
        LOG.info("In landlords edit");
        try{
            saveUser(landlord);
        }
        catch(Exception ex){
            return getEditViewModel(landlord, getObjectErrorList(ex), "edit");
        }
        return getListEntitiesModelView(landlordService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/landlord/new")
    public ModelAndView processNew(Landlord landlord) {
        LOG.info("In landlords new");
        try{
            saveUser(landlord);
        }
        catch(Exception ex){
            return getEditViewModel(landlord, getObjectErrorList(ex), "new");
        }
        return getListEntitiesModelView(landlordService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
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
    public String getListPath() { return "/landlords";}
    @Override
    public Dictionary<String, List<?>> getSelectLists(){
        Dictionary<String, List<?>> dictionary = new Hashtable<>();
        //Note used same attributeName "systemUser"
        dictionary.put("properties", propertyService.getProperties().stream().map(PropertySelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("legalEntity", legalEntityService.findAll().stream().map(LegalEntitySelectorDTO::new).collect(Collectors.toList()));
        return dictionary;
    }

    private void saveUser(Landlord landlord) throws Exception{
        landlord = landlordService.save(landlord);
        User user = landlord.getLegalEntity().getSystemUser();
        if(user == null){
            throw new Exception("Legal Entity does not have a User connected");
        }
        if(!user.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("Owner"))){
            user.getRoles().add(userService.getRoleByName("Owner"));
        }
        if(!user.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("Guest"))){
            user.getRoles().add(userService.getRoleByName("Guest"));
        }
        userService.saveUser(user);        
    }
}

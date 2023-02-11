package mscs.hms.controller;

import mscs.hms.model.Landlord;
import mscs.hms.model.Role;
import mscs.hms.model.Tenant;
import mscs.hms.model.User;
import mscs.hms.service.*;
import mscs.hms.dto.selectors.LegalEntitySelectorDTO;
import mscs.hms.dto.selectors.PropertySelectorDTO;
import mscs.hms.dto.selectors.PreferenceSelectorDTO;
import mscs.hms.controller.editors.PropertyListEditor;

import java.util.*;
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
    private IUserService userService;
    @Autowired
    private TenantService tenantService;

    @Autowired
    private LegalEntityService legalEntityService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PreferenceService preferenceService;

    @InitBinder
    public void customizeBinding (WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "properties",
                                    new PropertyListEditor(propertyService, true));        
    }

    @GetMapping("/tenants")
    public ModelAndView showTenants(Model model,
                                    @RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size,
                                    @RequestParam("search") Optional<String> search) {
        LOG.info("In tenants view");
        int currentPage = getCurrentPage(page);
        int pageSize = getPageSize(size);
        String searchString = getSearchString(search);
        return getListEntitiesModelView(tenantService.getAll(searchString, currentPage, pageSize));
    }    

    @GetMapping("/tenant_new")
    public ModelAndView newTenantForm() {
        LOG.info("In tenants new");
        return getEditViewModel(new Tenant(), "new");
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
        return getListEntitiesModelView(tenantService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/tenant/edit")
    public ModelAndView processEdit(Tenant tenant) {
        LOG.info("In tenants edit");
        try{
            saveUser(tenant);
        }
        catch(Exception ex){
            return getEditViewModel(tenant, getObjectErrorList(ex), "edit");
        }
        return getListEntitiesModelView(tenantService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/tenant/new")
    public ModelAndView processNew(Tenant tenant) {
        LOG.info("In tenants new");
        try{
            saveUser(tenant);
        }
        catch(Exception ex){
            return getEditViewModel(tenant, getObjectErrorList(ex), "new");
        }
        return getListEntitiesModelView(tenantService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
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
    public String getListPath() { return "/tenants";}
    @Override
    public Dictionary<String, List<?>> getSelectLists(){
        Dictionary<String, List<?>> dictionary = new Hashtable<>();
        //Note used same attributeName "systemUser"
        dictionary.put("properties", propertyService.getProperties().stream().map(PropertySelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("preference", preferenceService.findAll().stream().map(PreferenceSelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("legalEntity", legalEntityService.findAll().stream().map(LegalEntitySelectorDTO::new).collect(Collectors.toList()));
        return dictionary;
    }
    private void saveUser(Tenant tenant) throws Exception{
        tenant = tenantService.save(tenant);
        User user = tenant.getLegalEntity().getSystemUser();
        if(user == null){
            throw new Exception("Legal Entity does not have a User connected");
        }
        if(!user.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("Renter"))){
            user.getRoles().add(userService.getRoleByName("Renter"));
        }
        if(!user.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("Guest"))){
            user.getRoles().add(userService.getRoleByName("Guest"));
        }
        userService.saveUser(user);
        
    }
}

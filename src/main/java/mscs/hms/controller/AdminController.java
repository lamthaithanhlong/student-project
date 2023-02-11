package mscs.hms.controller;

import mscs.hms.dto.selectors.LegalEntitySelectorDTO;
import mscs.hms.model.Admin;
import mscs.hms.model.User;
import mscs.hms.model.Role;
import mscs.hms.service.AdminService;
import mscs.hms.dto.selectors.UserSelectorDTO;
import mscs.hms.service.IUserService;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import mscs.hms.service.LegalEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController extends AbsEntityController<Admin> {
    
    @Autowired
    private AdminService adminService;

    @Autowired
    private IUserService userService;

    @Autowired
    private LegalEntityService legalEntityService;

    @GetMapping("/admins")
    public ModelAndView showCompanies(Model model,
                                      @RequestParam("page") Optional<Integer> page,
                                      @RequestParam("size") Optional<Integer> size,
                                      @RequestParam("search") Optional<String> search) {
        LOG.info("In admin view");
        int currentPage = getCurrentPage(page);
        int pageSize = getPageSize(size);
        String searchString = getSearchString(search);
        return getListEntitiesModelView(adminService.getAll(searchString, currentPage, pageSize));
    }    

    @GetMapping("/admin_new")
    public ModelAndView newAdminForm() {
        LOG.info("In admins new");
        ModelAndView modelAndView = getEditViewModel(new Admin(), "new");
        return modelAndView;
    }    

    @GetMapping("/admin_edit/{id}")
    public ModelAndView editAdminForm(@PathVariable(value="id") final Integer adminId) {
        LOG.info("In admins edit");
        return getEditViewModel(adminService.getById(adminId), "edit");        
    }

    @PostMapping("/admin/delete") 
    public ModelAndView requestOTP( @RequestParam(value="id") Integer id) {
        LOG.info("In admins delete");
        adminService.deleteById(id);
        return getListEntitiesModelView(adminService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/admin/edit")
    public ModelAndView processEdit(Admin admin) {
        LOG.info("In admins edit");
        try{
            saveUser(admin);
        }
        catch(Exception ex){
            return getEditViewModel(admin, getObjectErrorList(ex), "edit");
        }
        return getListEntitiesModelView(adminService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/admin/new")
    public ModelAndView processNew(Admin admin) {
        LOG.info("In admins new");
        try{
            saveUser(admin);
        }
        catch(Exception ex){
            return getEditViewModel(admin, getObjectErrorList(ex), "new");
        }
        return getListEntitiesModelView(adminService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    } 
    
    @Override
    public Class<?> getClassType(){
        return Admin.class;
    }
    @Override
    public String getEditViewPath(){
        return "/admin_edit";
    }
    @Override
    public String getListViewPath(){
        return "/admin_list";
    }
    @Override
    public String getNewViewPath(){
        return "/admin_new";
    }
    @Override
    public String getCrudPath(){
        return "/admin";
    }
    @Override
    public String getListPath() { return "/admins";}
    @Override
    public Dictionary<String, List<?>> getSelectLists(){
        Dictionary<String, List<?>> dictionary = new Hashtable<>();
        //Note used same attributeName "systemUser"
        dictionary.put("systemUser", userService.findAllUsers().stream().map(UserSelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("legalEntity", legalEntityService.findAll().stream().map(LegalEntitySelectorDTO::new).collect(Collectors.toList()));
        return dictionary;
    }

    private void saveUser(Admin admin) throws Exception{
        adminService.save(admin);
        User user = admin.getLegalEntity().getSystemUser();
        if(user == null){
            throw new Exception("Legal Entity does not have a User connected");
        }
        if(!user.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("Admin"))){
            user.getRoles().add(userService.getRoleByName("Admin"));
        }
        if(!user.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("Guest"))){
            user.getRoles().add(userService.getRoleByName("Guest"));
        }
        userService.saveUser(user);
        
    }
}

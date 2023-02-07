package mscs.hms.controller;

import mscs.hms.model.Admin;
import mscs.hms.service.AddressService;
import mscs.hms.service.AdminService;
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
public class AdminController extends AbsEntityController<Admin> {
    
    @Autowired
    private AdminService adminService;

    @Autowired
    private IUserService userService;

    @GetMapping("/admins")
    public ModelAndView showCompanies(Model model) {
        LOG.info("In admins view");
        return getListEntitiesModelView(adminService.findAll());
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
        return getListEntitiesModelView(adminService.findAll());
    }

    @PostMapping("/admin/edit")
    public ModelAndView processEdit(Admin admin) {
        LOG.info("In admins edit");
        try{
            adminService.save(admin);
        }
        catch(Exception ex){
            return getEditViewModel(admin, getObjectErrorList(ex), "edit");
        }
        return getListEntitiesModelView(adminService.findAll());
    }

    @PostMapping("/admin/new")
    public ModelAndView processNew(Admin admin) {
        LOG.info("In admins new");
        try{
            adminService.save(admin);
        }
        catch(Exception ex){
            return getEditViewModel(admin, getObjectErrorList(ex), "edit");
        }
        return getListEntitiesModelView(adminService.findAll());
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
    public Dictionary<String, List<?>> getSelectLists(){
        Dictionary<String, List<?>> dictionary = new Hashtable<>();
        //Note used same attributeName "systemUser"
        dictionary.put("systemUser", userService.findAllUsers().stream().map(UserSelectorDTO::new).collect(Collectors.toList()));
        return dictionary;
    }
}

package mscs.hms.controller;

import mscs.hms.controller.editors.RolesListEditor;
import mscs.hms.dto.selectors.RoleSelectorDTO;
import mscs.hms.model.Role;
import mscs.hms.model.User;
import mscs.hms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class UserController extends AbsEntityController<User> {

    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @InitBinder
    public void customizeBinding (WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "roles", 
                                    new RolesListEditor(userService, true));
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        LOG.info("In register view");
        model.addAttribute("user", new User());
        
        List<Role> roles = new ArrayList<>();
        roles.add(userService.getRoleByName("Guest"));
        model.addAttribute("roles", roles);
        return "signup";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        LOG.info("Registration request received");
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles(new ArrayList<>());
        //Adding Guest Role to user if no role selected
        if (user.getRoles().isEmpty() ||
            user.getRoles().stream().anyMatch(x -> x.getName().equals("Guest"))) {
            Role role = userService.getRoleByName("Guest");
            if (role != null) {
                user.getRoles().add(role);
            }
        }

        userService.saveUser(user);

        return "register_success";
    }

    @GetMapping("/users")
    public ModelAndView showCompanies(Model model,
                                      @RequestParam("page") Optional<Integer> page,
                                      @RequestParam("size") Optional<Integer> size,
                                      @RequestParam("search") Optional<String> search) {
        LOG.info("In companies view");
        int currentPage = getCurrentPage(page);
        int pageSize = getPageSize(size);
        String searchString = getSearchString(search);
        return getListEntitiesModelView(userService.getAll(searchString, currentPage, pageSize));
    }    

    @GetMapping("/user_new")
    public ModelAndView newUserForm() {
        LOG.info("In users new");
        ModelAndView modelAndView = getEditViewModel(new User(), "new");
        return modelAndView;
    }    

    @GetMapping("/user_edit/{id}")
    public ModelAndView editUserForm(@PathVariable(value="id") final Long userId) {
        LOG.info("In users edit");
        return getEditViewModel(userService.get(userId), "edit");        
    }

    @PostMapping("/user/delete") 
    public ModelAndView requestOTP( @RequestParam(value="id") Long id) {
        LOG.info("In users delete");
        userService.delete(id);
        return getListEntitiesModelView(userService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/user/edit")
    public ModelAndView processEdit(User user) {
        LOG.info("In users edit");
        userService.saveUser(user);
        return getListEntitiesModelView(userService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/user/new")
    public ModelAndView processNew(User user) {
        LOG.info("In users new");
        userService.saveUser(user);
        return getListEntitiesModelView(userService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    } 

    @Override
    public Class<?> getClassType() {
        return User.class;
    }

    @Override
    public String getEditViewPath() {
        return "user_edit";
    }

    @Override
    public String getListViewPath(){
        return "/user_list";
    }
    @Override
    public String getNewViewPath(){
        return "/user_new";
    }
    @Override
    public String getCrudPath(){
        return "/user";
    }
    @Override
    public String getListPath() { return "/users";}
    @Override
    public Dictionary<String, List<?>> getSelectLists(){
        Dictionary<String, List<?>> dictionary = new Hashtable<>();
        //Note used same attribute name as the many to many relationship "roles"
        dictionary.put("roles", userService.getAllRoles().stream().map(RoleSelectorDTO::new).collect(Collectors.toList()));
        return dictionary;
    }
}

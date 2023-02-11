package mscs.hms.controller;

import mscs.hms.model.Company;
import mscs.hms.service.AddressService;
import mscs.hms.service.CompanyService;
import mscs.hms.dto.selectors.UserSelectorDTO;
import mscs.hms.dto.selectors.AddressSelectorDTO;
import mscs.hms.service.IUserService;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
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
public class CompanyController extends AbsEntityController<Company> {
    
    @Autowired
    private CompanyService companyService;

    @Autowired
    private IUserService userService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/companies")
    public ModelAndView showCompanies(Model model,
                                      @RequestParam("page") Optional<Integer> page,
                                      @RequestParam("size") Optional<Integer> size,
                                      @RequestParam("search") Optional<String> search) {
        LOG.info("In company view");
        int currentPage = getCurrentPage(page);
        int pageSize = getPageSize(size);
        String searchString = getSearchString(search);
        return getListEntitiesModelView(companyService.getAll(searchString, currentPage, pageSize));
    }    

    @GetMapping("/company_new")
    public ModelAndView newCompanyForm() {
        LOG.info("In companies new");
        ModelAndView modelAndView = getEditViewModel(new Company(), "new");
        return modelAndView;
    }    

    @GetMapping("/company_edit/{id}")
    public ModelAndView editCompanyForm(@PathVariable(value="id") final Integer companyId) {
        LOG.info("In companies edit");
        return getEditViewModel(companyService.get(companyId), "edit");        
    }

    @PostMapping("/company/delete") 
    public ModelAndView requestOTP( @RequestParam(value="id") Integer id) {
        LOG.info("In companies delete");
        companyService.delete(id);
        return getListEntitiesModelView(companyService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/company/edit")
    public ModelAndView processEdit(Company company) {
        LOG.info("In companies edit");
        try{
            companyService.save(company);
        }
        catch(Exception ex){
            return getEditViewModel(company, getObjectErrorList(ex), "edit");
        }
        return getListEntitiesModelView(companyService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/company/new")
    public ModelAndView processNew(Company company) {
        LOG.info("In companies new");
        try{
            companyService.save(company);
        }
        catch(Exception ex){
            return getEditViewModel(company, getObjectErrorList(ex), "new");
        }
        return getListEntitiesModelView(companyService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    } 
    
    @Override
    public Class<?> getClassType(){
        return Company.class;
    }
    @Override
    public String getEditViewPath(){
        return "/company_edit";
    }
    @Override
    public String getListViewPath(){
        return "/company_list";
    }
    @Override
    public String getNewViewPath(){
        return "/company_new";
    }
    @Override
    public String getCrudPath(){
        return "/company";
    }
    @Override
    public String getListPath() { return "/companies";}
    @Override
    public Dictionary<String, List<?>> getSelectLists(){
        Dictionary<String, List<?>> dictionary = new Hashtable<>();
        //Note used same attributeName "systemUser"
        dictionary.put("systemUser", userService.findAllUsers().stream().map(UserSelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("address", addressService.findAll().stream().map(AddressSelectorDTO::new).collect(Collectors.toList()));
        return dictionary;
    }
}

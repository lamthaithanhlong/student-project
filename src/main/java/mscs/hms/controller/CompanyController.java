package mscs.hms.controller;

import mscs.hms.entity.Company;
import mscs.hms.service.CompanyService;
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

    @GetMapping("/companies")
    public ModelAndView showCompanies(Model model) {
        LOG.info("In companies view");
        return getListEntitiesModelView(companyService.findAll());
    }    

    @GetMapping("/company_new")
    public ModelAndView newCompanyForm() {
        LOG.info("In companies new");
        return getEditViewModel(new Company(), "new");
    }    

    @GetMapping("/company_edit/{id}")
    public ModelAndView editCompanyForm(@PathVariable(value="id") final Integer companyId) {
        LOG.info("In companies edit");
        return getEditViewModel(companyService.get(companyId), "new");        
    }

    @PostMapping("/company/delete") 
    public ModelAndView requestOTP( @RequestParam(value="id") Integer id) {
        LOG.info("In companies delete");
        companyService.delete(id);
        return getListEntitiesModelView(companyService.findAll());
    }

    @PostMapping("/company/edit")
    public ModelAndView processEdit(Company company) {
        LOG.info("In companies edit");
        companyService.save(company);
        return getListEntitiesModelView(companyService.findAll());
    }

    @PostMapping("/company/new")
    public ModelAndView processNew(Company company) {
        LOG.info("In companies new");
        companyService.save(company);
        return getListEntitiesModelView(companyService.findAll());
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
        return "/companies";
    }
    @Override
    public String getNewViewPath(){
        return "/company_new";
    }
    @Override
    public String getCrudPath(){
        return "/company";
    }
}

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
public class CompanyController extends AbsBaseController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public ModelAndView showCompanies(Model model) {
        LOG.info("In companies view");
        ModelAndView modelAndView = getListCompaniesModelView();        
        addViewGenerationProperties(modelAndView);
        return modelAndView;
    }    

    @GetMapping("/company_new")
    public ModelAndView newCompanyForm() {
        LOG.info("In companies new");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("object", new Company());
        modelAndView.addObject("action", "new");
        modelAndView.setViewName("company_edit");
        addViewGenerationProperties(modelAndView);
        return modelAndView;
    }

    @GetMapping("/company_edit/{id}")
    public ModelAndView editCompanyForm(@PathVariable(value="id") final Integer companyId) {
        LOG.info("In companies edit");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("object", companyService.get(companyId));        
        modelAndView.addObject("action", "edit");
        modelAndView.setViewName("company_edit");
        addViewGenerationProperties(modelAndView);
        return modelAndView;
    }

    @PostMapping("/company/delete") 
    public ModelAndView requestOTP( @RequestParam(value="id") Integer id) {
        LOG.info("In companies delete");
        companyService.delete(id);
        ModelAndView modelAndView =  getListCompaniesModelView();
        addViewGenerationProperties(modelAndView);
        return modelAndView;
    }

    @PostMapping("/company/edit")
    public ModelAndView processEdit(Company company) {
        LOG.info("In companies edit");
        companyService.save(company);
        ModelAndView modelAndView =  getListCompaniesModelView();
        addViewGenerationProperties(modelAndView);
        return modelAndView;
    }

    @PostMapping("/company/new")
    public ModelAndView processNew(Company company) {
        LOG.info("In companies new");
        companyService.save(company);
        ModelAndView modelAndView =  getListCompaniesModelView();
        addViewGenerationProperties(modelAndView);
        return modelAndView;
    }

    protected void addViewGenerationProperties(ModelAndView modelAndView) {
        modelAndView.addObject("fields", getPrivateFields(Company.class));
        modelAndView.addObject("listViewPath", "/companies");
        modelAndView.addObject("newViewPath", "/company_new");
        modelAndView.addObject("editViewPath", "/company_edit");
        modelAndView.addObject("crudPath", "/company");
    }
    
    private ModelAndView getListCompaniesModelView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("objects", companyService.findAll());
        modelAndView.setViewName("company_list");
        return modelAndView;
    }
    
}

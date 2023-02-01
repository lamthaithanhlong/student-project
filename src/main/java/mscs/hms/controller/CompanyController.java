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
        return modelAndView;
    }

    @GetMapping("/company_new")
    public ModelAndView newCompanyForm() {
        LOG.info("In companies new");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("company", new Company());
        modelAndView.addObject("action", "new");
        modelAndView.setViewName("company_edit");
        return modelAndView;
    }

    @GetMapping("/company_edit/{companyId}")
    public ModelAndView editCompanyForm(@PathVariable(value="companyId") final Integer companyId) {
        LOG.info("In companies edit");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("company", companyService.get(companyId));        
        modelAndView.addObject("action", "edit");
        modelAndView.setViewName("company_edit");
        return modelAndView;
    }

    @PostMapping("/company/delete") 
    public ModelAndView requestOTP( @RequestParam(value="id") Integer id) {
        LOG.info("In companies delete");
        companyService.delete(id);
        return getListCompaniesModelView();
    }

    @PostMapping("/company/edit")
    public ModelAndView processEdit(Company company) {
        LOG.info("In companies edit");
        companyService.save(company);
        return getListCompaniesModelView();
    }

    @PostMapping("/company/new")
    public ModelAndView processNew(Company company) {
        LOG.info("In companies new");
        companyService.save(company);
        return getListCompaniesModelView();
    }

    private ModelAndView getListCompaniesModelView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("companies", companyService.findAll());
        modelAndView.setViewName("company_list");
        return modelAndView;
    }
    
}

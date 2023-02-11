package mscs.hms.controller;

import mscs.hms.model.Apartment;
import mscs.hms.service.AddressService;
import mscs.hms.service.ApartmentService;
import mscs.hms.dto.selectors.AddressSelectorDTO;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;

@Controller
public class ApartmentController extends AbsEntityController<Apartment> {
    
    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/apartments")
    public ModelAndView showApartments(Model model,
                                       @RequestParam("page") Optional<Integer> page,
                                       @RequestParam("size") Optional<Integer> size,
                                       @RequestParam("search") Optional<String> search) {
        LOG.info("In apartments view");
        int currentPage = getCurrentPage(page);
        int pageSize = getPageSize(size);
        String searchString = getSearchString(search);
        return getListEntitiesModelView(apartmentService.getAll(searchString, currentPage, pageSize));
    }    

    @GetMapping("/apartment_new")
    public ModelAndView newApartmentForm() {
        LOG.info("In apartments new");
        return getEditViewModel(new Apartment(), "new");
    }    

    @GetMapping("/apartment_edit/{id}")
    public ModelAndView editApartmentForm(@PathVariable(value="id") final Integer apartmentId) {
        LOG.info("In apartments edit");
        return getEditViewModel(apartmentService.get(apartmentId), "edit");        
    }

    @PostMapping("/apartment/delete") 
    public ModelAndView requestOTP( @RequestParam(value="id") Integer id) {
        LOG.info("In apartments delete");
        apartmentService.delete(id);
        return getListEntitiesModelView(apartmentService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/apartment/edit")
    public ModelAndView processEdit(@Valid @ModelAttribute("apartment") Apartment apartment, BindingResult bindingResult) {
        LOG.info("In apartments edit");
        if(bindingResult.hasErrors()) {
            return getEditViewModel(apartment, bindingResult.getAllErrors(), "edit");            
        }
        try{
            apartmentService.save(apartment);
        }
        catch(Exception ex){
            return getEditViewModel(apartment, getObjectErrorList(ex), "edit");
        }                
        return getListEntitiesModelView(apartmentService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/apartment/new")
    public ModelAndView processNew(Apartment apartment) {
        LOG.info("In apartments new");
        try{
            apartmentService.save(apartment);
        }
        catch(Exception ex){
            return getEditViewModel(apartment, getObjectErrorList(ex), "new");
        }
        return getListEntitiesModelView(apartmentService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    } 
    
    @Override
    public Class<?> getClassType(){
        return Apartment.class;
    }
    @Override
    public String getEditViewPath(){
        return "/apartment_edit";
    }
    @Override
    public String getListViewPath(){
        return "/apartment_list";
    }
    @Override
    public String getNewViewPath(){
        return "/apartment_new";
    }
    @Override
    public String getCrudPath(){
        return "/apartment";
    }
    @Override
    public String getListPath() { return "/apartments";}
    @Override
    public Dictionary<String, List<?>> getSelectLists(){
        Dictionary<String, List<?>> dictionary = new Hashtable<>();
        dictionary.put("address", addressService.findAll().stream().map(AddressSelectorDTO::new).collect(Collectors.toList()));
        return dictionary;
    }
}


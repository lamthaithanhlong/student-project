package mscs.hms.controller;

import mscs.hms.model.Apartment;
import mscs.hms.service.AddressService;
import mscs.hms.service.ApartmentService;
import mscs.hms.dto.selectors.AddressSelectorDTO;
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
public class ApartmentController extends AbsEntityController<Apartment> {
    
    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/apartments")
    public ModelAndView showApartments(Model model) {
        LOG.info("In apartments view");
        return getListEntitiesModelView(apartmentService.findAll());
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
        return getListEntitiesModelView(apartmentService.findAll());
    }

    @PostMapping("/apartment/edit")
    public ModelAndView processEdit(Apartment apartment) {
        LOG.info("In apartments edit");
        apartmentService.save(apartment);
        return getListEntitiesModelView(apartmentService.findAll());
    }

    @PostMapping("/apartment/new")
    public ModelAndView processNew(Apartment apartment) {
        LOG.info("In apartments new");
        apartmentService.save(apartment);
        return getListEntitiesModelView(apartmentService.findAll());
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
        return "/apartments";
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
    public Dictionary<String, List<?>> getSelectLists(){
        Dictionary<String, List<?>> dictionary = new Hashtable<>();
        dictionary.put("address", addressService.findAll().stream().map(AddressSelectorDTO::new).collect(Collectors.toList()));
        return dictionary;
    }
}


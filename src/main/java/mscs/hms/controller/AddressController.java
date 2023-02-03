package mscs.hms.controller;

import mscs.hms.model.Address;
import mscs.hms.service.AddressService;
import mscs.hms.service.IUserService;
import java.util.Dictionary;
import java.util.Hashtable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddressController extends AbsEntityController<Address> {
    
    @Autowired
    private AddressService addressService;

    @GetMapping("/addresses")
    public ModelAndView showCompanies(Model model) {
        LOG.info("In addresses view");
        return getListEntitiesModelView(addressService.findAll());
    }    

    @GetMapping("/address_new")
    public ModelAndView newAddressForm() {
        LOG.info("In addresses new");
        ModelAndView modelAndView = getEditViewModel(new Address(), "new");
        return modelAndView;
    }    

    @GetMapping("/address_edit/{id}")
    public ModelAndView editAddressForm(@PathVariable(value="id") final Integer addressId) {
        LOG.info("In addresses edit");
        return getEditViewModel(addressService.get(addressId), "new");        
    }

    @PostMapping("/address/delete") 
    public ModelAndView requestOTP( @RequestParam(value="id") Integer id) {
        LOG.info("In addresses delete");
        addressService.delete(id);
        return getListEntitiesModelView(addressService.findAll());
    }

    @PostMapping("/address/edit")
    public ModelAndView processEdit(Address address) {
        LOG.info("In addresses edit");
        addressService.save(address);
        return getListEntitiesModelView(addressService.findAll());
    }

    @PostMapping("/address/new")
    public ModelAndView processNew(Address address) {
        LOG.info("In addresses new");
        addressService.save(address);
        return getListEntitiesModelView(addressService.findAll());
    } 
    
    @Override
    public Class<?> getClassType(){
        return Address.class;
    }
    @Override
    public String getEditViewPath(){
        return "/address_edit";
    }
    @Override
    public String getListViewPath(){
        return "/addresses";
    }
    @Override
    public String getNewViewPath(){
        return "/address_new";
    }
    @Override
    public String getCrudPath(){
        return "/address";
    }
    @Override
    public Dictionary<String, Iterable<?>> getSelectLists(){
        Dictionary<String, Iterable<?>> dictionary = new Hashtable<>();
        return dictionary;
    }
}

package mscs.hms.controller;

import mscs.hms.model.Address;
import mscs.hms.service.AddressService;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ModelAndView showCompanies(Model model,
                                      @RequestParam("page") Optional<Integer> page,
                                      @RequestParam("size") Optional<Integer> size,
                                      @RequestParam("search") Optional<String> search) {
        LOG.info("In addresses view");
        int currentPage = getCurrentPage(page);
        int pageSize = getPageSize(size);
        String searchString = getSearchString(search);
        Page<Address> addresses = addressService.getAll(searchString, currentPage, pageSize);
        return getListEntitiesModelView(addresses);
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
        return getEditViewModel(addressService.get(addressId), "edit");        
    }

    @PostMapping("/address/delete") 
    public ModelAndView requestOTP( @RequestParam(value="id") Integer id) {
        LOG.info("In addresses delete");
        addressService.delete(id);
        return getListEntitiesModelView(addressService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/address/edit")
    public ModelAndView processEdit(Address address) {
        LOG.info("In addresses edit");
        try{
            addressService.save(address);
        }
        catch(Exception ex){
            return getEditViewModel(address, getObjectErrorList(ex), "edit");
        }        
        return getListEntitiesModelView(addressService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/address/new")
    public ModelAndView processNew(Address address) {
        LOG.info("In addresses new");
        try{
            addressService.save(address);
        }
        catch(Exception ex){
            return getEditViewModel(address, getObjectErrorList(ex), "new");
        }
        return getListEntitiesModelView(addressService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
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
        return "/address_list";
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
    public String getListPath() { return "/addresses";}
    @Override
    public Dictionary<String, List<?>> getSelectLists(){
        Dictionary<String, List<?>> dictionary = new Hashtable<>();
        return dictionary;
    }
}

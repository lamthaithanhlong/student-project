package mscs.hms.controller;

import mscs.hms.model.Property;
import mscs.hms.service.AddressService;
import mscs.hms.service.PropertyService;
import mscs.hms.dto.selectors.AddressSelectorDTO;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PropertyController extends AbsEntityController<Property> {
    
    @Autowired
    private PropertyService propertyService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/properties-list")
    public ModelAndView showPropertys(Model model,
                                    @RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size,
                                    @RequestParam("search") Optional<String> search) {
        LOG.info("In property view");
        int currentPage = getCurrentPage(page);
        int pageSize = getPageSize(size);
        String searchString = getSearchString(search);
        return getListEntitiesModelView(propertyService.getAll(searchString, currentPage, pageSize));
    }    

    @Override
    public Class<?> getClassType(){
        return Property.class;
    }
    @Override
    public String getEditViewPath(){
        return null;
    }
    @Override
    public String getListViewPath(){
        return "/property_list";
    }
    @Override
    public String getNewViewPath(){
        return null;
    }
    @Override
    public String getCrudPath(){
        return null;
    }
    @Override
    public String getListPath() { return "/properties-list";}
    @Override
    public Dictionary<String, List<?>> getSelectLists(){
        Dictionary<String, List<?>> dictionary = new Hashtable<>();
        dictionary.put("address", addressService.findAll().stream().map(AddressSelectorDTO::new).collect(Collectors.toList()));
       return dictionary;
    }
}

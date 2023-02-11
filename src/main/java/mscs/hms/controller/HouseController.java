package mscs.hms.controller;

import mscs.hms.model.House;
import mscs.hms.service.AddressService;
import mscs.hms.service.HouseService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HouseController extends AbsEntityController<House> {
    
    @Autowired
    private HouseService houseService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/houses")
    public ModelAndView showHouses(Model model,
                                   @RequestParam("page") Optional<Integer> page,
                                   @RequestParam("size") Optional<Integer> size,
                                   @RequestParam("search") Optional<String> search) {
        LOG.info("In houses view");
        int currentPage = getCurrentPage(page);
        int pageSize = getPageSize(size);
        String searchString = getSearchString(search);
        return getListEntitiesModelView(houseService.getAll(searchString, currentPage, pageSize));
    }    

    @GetMapping("/house_new")
    public ModelAndView newHouseForm() {
        LOG.info("In houses new");
        return getEditViewModel(new House(), "new");
    }    

    @GetMapping("/house_edit/{id}")
    public ModelAndView editHouseForm(@PathVariable(value="id") final Integer houseId) {
        LOG.info("In houses edit");
        return getEditViewModel(houseService.get(houseId), "edit");        
    }

    @PostMapping("/house/delete") 
    public ModelAndView requestOTP( @RequestParam(value="id") Integer id) {
        LOG.info("In houses delete");
        houseService.delete(id);
        return getListEntitiesModelView(houseService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/house/edit")
    public ModelAndView processEdit(House house) {
        LOG.info("In houses edit");
        try{
            houseService.save(house);
        }
        catch(Exception ex){
            return getEditViewModel(house, getObjectErrorList(ex), "edit");
        }
        return getListEntitiesModelView(houseService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    }

    @PostMapping("/house/new")
    public ModelAndView processNew(House house) {
        LOG.info("In houses new");
        try{
            houseService.save(house);
        }
        catch(Exception ex){
            return getEditViewModel(house, getObjectErrorList(ex), "new");
        }
        return getListEntitiesModelView(houseService.getAll(null, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE));
    } 
    
    @Override
    public Class<?> getClassType(){
        return House.class;
    }
    @Override
    public String getEditViewPath(){
        return "/house_edit";
    }
    @Override
    public String getListViewPath(){
        return "/house_list";
    }
    @Override
    public String getNewViewPath(){
        return "/house_new";
    }
    @Override
    public String getCrudPath(){
        return "/house";
    }
    @Override
    public String getListPath() { return "/houses";}
    @Override
    public Dictionary<String, List<?>> getSelectLists(){
        Dictionary<String, List<?>> dictionary = new Hashtable<>();
        dictionary.put("address", addressService.findAll().stream().map(AddressSelectorDTO::new).collect(Collectors.toList()));        
        return dictionary;
    }
}

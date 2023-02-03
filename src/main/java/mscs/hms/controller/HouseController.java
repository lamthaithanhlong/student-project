package mscs.hms.controller;

import mscs.hms.model.House;
import mscs.hms.service.AddressService;
import mscs.hms.service.HouseService;

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
public class HouseController extends AbsEntityController<House> {
    
    @Autowired
    private HouseService houseService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/houses")
    public ModelAndView showHouses(Model model) {
        LOG.info("In houses view");
        return getListEntitiesModelView(houseService.findAll());
    }    

    @GetMapping("/house_new")
    public ModelAndView newHouseForm() {
        LOG.info("In houses new");
        return getEditViewModel(new House(), "new");
    }    

    @GetMapping("/house_edit/{id}")
    public ModelAndView editHouseForm(@PathVariable(value="id") final Integer houseId) {
        LOG.info("In houses edit");
        return getEditViewModel(houseService.get(houseId), "new");        
    }

    @PostMapping("/house/delete") 
    public ModelAndView requestOTP( @RequestParam(value="id") Integer id) {
        LOG.info("In houses delete");
        houseService.delete(id);
        return getListEntitiesModelView(houseService.findAll());
    }

    @PostMapping("/house/edit")
    public ModelAndView processEdit(House house) {
        LOG.info("In houses edit");
        houseService.save(house);
        return getListEntitiesModelView(houseService.findAll());
    }

    @PostMapping("/house/new")
    public ModelAndView processNew(House house) {
        LOG.info("In houses new");
        houseService.save(house);
        return getListEntitiesModelView(houseService.findAll());
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
        return "/houses";
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
    public Dictionary<String, Iterable<?>> getSelectLists(){
        Dictionary<String, Iterable<?>> dictionary = new Hashtable<>();
        dictionary.put("address", addressService.findAll());
        return dictionary;
    }
}

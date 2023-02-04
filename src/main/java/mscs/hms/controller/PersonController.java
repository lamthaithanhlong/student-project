package mscs.hms.controller;

import mscs.hms.model.Person;
import mscs.hms.service.AddressService;
import mscs.hms.service.PersonService;
import mscs.hms.service.IUserService;
import mscs.hms.dto.selectors.UserSelectorDTO;
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
public class PersonController extends AbsEntityController<Person> {
    
    @Autowired
    private PersonService personService;

    @Autowired
    private IUserService userService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/persons")
    public ModelAndView showPersons(Model model) {
        LOG.info("In persons view");
        return getListEntitiesModelView(personService.findAll());
    }    

    @GetMapping("/person_new")
    public ModelAndView newPersonForm() {
        LOG.info("In persons new");
        return getEditViewModel(new Person(), "new");
    }    

    @GetMapping("/person_edit/{id}")
    public ModelAndView editPersonForm(@PathVariable(value="id") final Integer personId) {
        LOG.info("In persons edit");
        return getEditViewModel(personService.get(personId), "edit");        
    }

    @PostMapping("/person/delete") 
    public ModelAndView requestOTP( @RequestParam(value="id") Integer id) {
        LOG.info("In persons delete");
        personService.delete(id);
        return getListEntitiesModelView(personService.findAll());
    }

    @PostMapping("/person/edit")
    public ModelAndView processEdit(Person person) {
        LOG.info("In persons edit");
        personService.save(person);
        return getListEntitiesModelView(personService.findAll());
    }

    @PostMapping("/person/new")
    public ModelAndView processNew(Person person) {
        LOG.info("In persons new");
        personService.save(person);
        return getListEntitiesModelView(personService.findAll());
    } 
    
    @Override
    public Class<?> getClassType(){
        return Person.class;
    }
    @Override
    public String getEditViewPath(){
        return "/person_edit";
    }
    @Override
    public String getListViewPath(){
        return "/persons";
    }
    @Override
    public String getNewViewPath(){
        return "/person_new";
    }
    @Override
    public String getCrudPath(){
        return "/person";
    }
    @Override
    public Dictionary<String, List<?>> getSelectLists(){
        Dictionary<String, List<?>> dictionary = new Hashtable<>();
        //Note used same attributeName "systemUser"
        dictionary.put("systemUser", userService.findAllUsers().stream().map(UserSelectorDTO::new).collect(Collectors.toList()));
        dictionary.put("address", addressService.findAll().stream().map(AddressSelectorDTO::new).collect(Collectors.toList()));
       return dictionary;
    }
}

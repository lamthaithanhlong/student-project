package mscs.hms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mscs.hms.entity.Person;
import mscs.hms.repository.PersonRepository;
import mscs.hms.service.PersonService;

@Service
public class PersonServiceImpl extends AbsBaseService implements PersonService {
    
    @Autowired
    PersonRepository personRepository;    

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person get(Integer id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void delete(Integer id) {
        personRepository.deleteById(id);
    }
}

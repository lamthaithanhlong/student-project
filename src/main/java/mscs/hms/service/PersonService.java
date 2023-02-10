package mscs.hms.service;

import mscs.hms.model.Person;
import org.springframework.data.domain.Page;

public interface PersonService {
    public Person savePerson(Person person);
    public Person get(Integer id);
    public void delete(Integer id);
    public Iterable<Person> findAll();
    public Person save(Person person);
    public Page<Person> getAll(String searchString, Integer page, Integer pageSize);
}

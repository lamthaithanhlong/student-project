package mscs.hms.service;

import mscs.hms.model.Person;

public interface PersonService {
    public Person savePerson(Person person);
    public Person get(Integer id);
    public void delete(Integer id);
    public Iterable<Person> findAll();
    public Person save(Person person);
}

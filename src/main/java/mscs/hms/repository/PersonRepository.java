package mscs.hms.repository;

import org.springframework.data.repository.CrudRepository;
import mscs.hms.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {    
}

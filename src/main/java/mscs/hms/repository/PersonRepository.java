package mscs.hms.repository;

import org.springframework.data.repository.CrudRepository;
import mscs.hms.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {    
}

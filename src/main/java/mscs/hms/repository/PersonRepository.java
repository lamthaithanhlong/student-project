package mscs.hms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import mscs.hms.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    public Page<Person> findByFirstNameContainsIgnoreCase(String text, PageRequest pageRequest);
}

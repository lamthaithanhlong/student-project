package mscs.hms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import mscs.hms.model.Person;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("select p from Person p where p.firstName =:searchString or " +
            "p.lastName = :searchString or p.phoneNumber = :searchString or p.systemUser.username = :searchString or p.legalEntityName =:searchString")
    Page<Person> searchPerson(String searchString, PageRequest pageRequest);
}

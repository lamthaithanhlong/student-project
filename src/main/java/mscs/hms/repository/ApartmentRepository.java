package mscs.hms.repository;

import org.springframework.data.repository.CrudRepository;
import mscs.hms.entity.Apartment;

public interface ApartmentRepository extends CrudRepository<Apartment, Integer> {    
}

package mscs.hms.repository;

import org.springframework.data.repository.CrudRepository;
import mscs.hms.model.Apartment;

public interface ApartmentRepository extends CrudRepository<Apartment, Integer> {    
}

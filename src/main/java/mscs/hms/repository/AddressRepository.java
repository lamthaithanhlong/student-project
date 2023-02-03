package mscs.hms.repository;

import org.springframework.data.repository.CrudRepository;
import mscs.hms.model.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {    
}

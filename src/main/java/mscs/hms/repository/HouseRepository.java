package mscs.hms.repository;

import org.springframework.data.repository.CrudRepository;
import mscs.hms.model.House;

public interface HouseRepository extends CrudRepository<House, Integer> {    
}

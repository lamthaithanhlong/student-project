package mscs.hms.repository;

import org.springframework.data.repository.CrudRepository;
import mscs.hms.entity.House;

public interface HouseRepository extends CrudRepository<House, Integer> {    
}

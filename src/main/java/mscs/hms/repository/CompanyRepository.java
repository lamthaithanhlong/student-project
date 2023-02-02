package mscs.hms.repository;

import org.springframework.data.repository.CrudRepository;
import mscs.hms.entity.Company;

public interface CompanyRepository extends CrudRepository<Company, Integer> {    
}

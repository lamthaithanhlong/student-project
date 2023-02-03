package mscs.hms.repository;

import org.springframework.data.repository.CrudRepository;
import mscs.hms.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Integer> {    
}

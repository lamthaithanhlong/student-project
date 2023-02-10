package mscs.hms.repository;

import mscs.hms.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import mscs.hms.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    public Page<Company> findByCompanyNameContainsIgnoreCase(String text, PageRequest pageRequest);
}

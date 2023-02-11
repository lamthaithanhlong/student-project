package mscs.hms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import mscs.hms.model.Company;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    @Query("select c from Company c, LegalEntity l , User u where c.legalEntityName = l.legalEntityName or l.systemUser.username = :searchString  or c.phoneNumber =:searchString or c.legalEntityName = :searchString or " +
            "c.companyName = :searchString or l.systemUser.username = :searchString")
    Page<Company> searchCompany(String searchString, PageRequest pageRequest);
}

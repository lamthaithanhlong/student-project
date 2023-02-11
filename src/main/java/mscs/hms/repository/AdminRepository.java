package mscs.hms.repository;

import mscs.hms.model.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    @Query(value = "select a from Admin a , LegalEntity l where a.legalEntity = l and a.name =:searchString or l.legalEntityName =:searchString")
    Page<Admin> searchAdmin(String searchString, PageRequest pageRequest);
}

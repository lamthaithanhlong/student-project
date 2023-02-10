package mscs.hms.repository;

import mscs.hms.model.Tenant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TenantRepository extends JpaRepository<Tenant, Integer> {
    @Query("select t from Tenant t where t.name =:searchString or t.legalEntity.legalEntityName = :searchString")
    Page<Tenant> searchTenant(String searchString, PageRequest pageRequest);
}

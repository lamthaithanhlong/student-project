package mscs.hms.repository;

import mscs.hms.model.Tenant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Integer> {
    public Page<Tenant> findByNameContainsIgnoreCase(String text, PageRequest pageRequest);
}

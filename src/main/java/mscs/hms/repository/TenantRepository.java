package mscs.hms.repository;

import mscs.hms.entity.Tenant;
import org.springframework.data.repository.CrudRepository;

public interface TenantRepository extends CrudRepository<Tenant, Integer> {
}

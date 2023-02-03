package mscs.hms.repository;

import mscs.hms.model.Tenant;
import org.springframework.data.repository.CrudRepository;

public interface TenantRepository extends CrudRepository<Tenant, Integer> {
}

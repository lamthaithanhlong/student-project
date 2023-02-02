package mscs.hms.service;

import mscs.hms.entity.Tenant;

public interface TenantService {
    public Tenant save(Tenant tenant);
    public Tenant getById(Integer id);
    public void deleteById(Integer id);
    public Iterable<Tenant> findAll();
}

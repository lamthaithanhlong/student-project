package mscs.hms.service;

import mscs.hms.model.Tenant;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TenantService {
    public Tenant save(Tenant tenant);
    public Tenant getById(Integer id);
    public void deleteById(Integer id);
    public List<Tenant> findAll();
    public Page<Tenant> getAll(String searchString, Integer page, Integer pageSize);
}

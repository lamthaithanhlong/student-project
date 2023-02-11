package mscs.hms.service.impl;

import mscs.hms.model.Tenant;
import mscs.hms.repository.TenantRepository;
import mscs.hms.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Override
    public Tenant save(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    @Override
    public Tenant getById(Integer id) {
        return tenantRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        tenantRepository.deleteById(id);
    }

    @Override
    public List<Tenant> findAll() {
        return tenantRepository.findAll();
    }
    public Page<Tenant> getAll(String searchString, Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page,pageSize);
        if(searchString == null || searchString.isBlank())
            return tenantRepository.findAll(pageRequest);
        else
            return tenantRepository.searchTenant(searchString.toLowerCase(), pageRequest);
    }
}

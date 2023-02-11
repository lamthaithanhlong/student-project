package mscs.hms.service.impl;

import mscs.hms.model.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import mscs.hms.repository.AdminRepository;
import mscs.hms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin getById(Integer id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        adminRepository.deleteById(id);
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Page<Admin> getAll(String searchString, Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page,pageSize);
        if(searchString == null || searchString.isBlank())
            return adminRepository.findAll(pageRequest);
        else
            return adminRepository.searchAdmin(searchString.toLowerCase(), pageRequest);
    }
}

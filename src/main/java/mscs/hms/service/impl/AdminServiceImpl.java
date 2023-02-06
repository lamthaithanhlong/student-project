package mscs.hms.service.impl;

import mscs.hms.model.Admin;
import org.springframework.stereotype.Service;
import mscs.hms.repository.AdminRepository;
import mscs.hms.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

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
    public Iterable<Admin> findAll() {
        return adminRepository.findAll();
    }
}

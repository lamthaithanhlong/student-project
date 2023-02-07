package mscs.hms.service;


import mscs.hms.model.Admin;
import java.util.List;
public interface AdminService {
    public Admin save(Admin admin);
    public Admin getById(Integer id);
    public void deleteById(Integer id);
    public List<Admin> findAll();
}

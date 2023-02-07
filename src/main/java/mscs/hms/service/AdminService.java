package mscs.hms.service;


import mscs.hms.model.Admin;

public interface AdminService {
    public Admin save(Admin admin);
    public Admin getById(Integer id);
    public void deleteById(Integer id);
    public Iterable<Admin> findAll();
}

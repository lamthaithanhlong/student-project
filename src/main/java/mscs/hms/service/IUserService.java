package mscs.hms.service;

import mscs.hms.model.Role;
import mscs.hms.model.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    public User saveUser(User user);

    public List<User> findAllUsers();

    public List<User> getAllUsersByRole(String roleName);

    public List<Role> getAllRoles();

    public Role getRoleByName(String name);

    public Role getRoleById(Integer name);

    public User get(Long id);

    public void delete(Long id);
    public Page<User> getAll(String searchString, Integer page, Integer pageSize);
}

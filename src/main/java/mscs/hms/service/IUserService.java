package mscs.hms.service;

import mscs.hms.entity.Role;
import mscs.hms.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    public User saveUser(User user);

    public List<User> findAllUsers();

    public List<Role> getAllRoles();

    public Role getRoleByName(String name);
}

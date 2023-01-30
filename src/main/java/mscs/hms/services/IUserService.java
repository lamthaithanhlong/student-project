package mscs.hms.services;

import mscs.hms.entity.Role;
import mscs.hms.entity.User;

import java.util.List;

public interface IUserService {

    public User saveUser(User user);

    public User getUserByUsername(String userName);

    public List<User> findAllUsers();

    public List<Role> getAllRoles();

    public Role getRoleByName(String name);
}

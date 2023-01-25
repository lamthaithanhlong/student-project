package mscs.hms.services;

import mscs.hms.models.Role;
import mscs.hms.models.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserService {

    public UserInfo saveUser(UserInfo user);

    public List<UserInfo> findAllUsers();

    public List<Role> getAllRoles();

    public Role getAllRoleByName(String name);
}

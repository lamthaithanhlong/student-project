package mscs.hms.services;

import mscs.hms.entity.Role;
import mscs.hms.entity.User;
import mscs.hms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String userName) {
        return userRepository.getUserByUsername(userName);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Role> getAllRoles() {
        return userRepository.getAllRoles();
    }

    @Override
    public Role getRoleByName(String name) {
        return userRepository.getRoleByName(name);
    }
}

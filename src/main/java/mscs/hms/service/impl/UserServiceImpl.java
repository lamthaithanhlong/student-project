package mscs.hms.service.impl;

import mscs.hms.model.Role;
import mscs.hms.model.User;
import mscs.hms.repository.UserRepository;
import mscs.hms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends AbsBaseService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        LOG.info("Loading user with name " + username);

        User user = userRepository.getUserByUsername(username);

        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
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

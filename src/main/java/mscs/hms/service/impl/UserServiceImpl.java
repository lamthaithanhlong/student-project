package mscs.hms.service.impl;

import mscs.hms.model.Role;
import mscs.hms.model.User;
import mscs.hms.repository.UserRepository;
import mscs.hms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public List<User> getAllUsersByRole(String roleName) {
        return userRepository.findAllByRolesContainingIgnoreCase(roleName);
    }

    @Override
    public List<Role> getAllRoles() {
        return userRepository.getAllRoles();
    }

    @Override
    public Role getRoleByName(String name) {
        return userRepository.getRoleByName(name);
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Role getRoleById(Integer id) {
        return userRepository.getAllRoles().stream().filter(x-> x.getId().equals(id)).findAny().orElse(null);
    }

    public Page<User> getAll(String searchString, Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page,pageSize);
        if(searchString == null || searchString.isBlank())
            return userRepository.findAll(pageRequest);
        else
            return userRepository.searchUser(searchString.toLowerCase(), pageRequest);
    }
}

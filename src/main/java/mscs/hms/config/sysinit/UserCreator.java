package mscs.hms.config.sysinit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import mscs.hms.model.Role;
import mscs.hms.model.User;
import mscs.hms.repository.UserRepository;
import mscs.hms.repository.RoleRepository;

import jakarta.annotation.PostConstruct;

@Component
class UserCreator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void init() {
        List<Role> roles = new ArrayList<>();
        Role role = userRepository.getRoleByName("Admin");
        if(role == null){
            role = new Role();
            role.setName("Admin");
            role = roleRepository.save(role);
        }
        roles.add(role);

        role = userRepository.getRoleByName("Guest");
        if(role == null){
            role = new Role();
            role.setName("Guest");
            role = roleRepository.save(role);
        }
        roles.add(role);

        User existingUser = userRepository.getUserByUsername("admin");
        if(existingUser != null)
            return;

        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword(bCryptPasswordEncoder.encode("admin"));
        adminUser.setEmail("admin@admin.com");
        adminUser.setFirstName("System");
        adminUser.setPhoneNumber("123-456-7890");
        adminUser.setLastName("Main User");
        adminUser.setRoles(roles);
        userRepository.save(adminUser);
    }
}

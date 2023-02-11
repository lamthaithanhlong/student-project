package mscs.hms.config.sysinit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import mscs.hms.model.Role;
import mscs.hms.model.User;
import mscs.hms.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
class UserCreator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void init() {
        List<Role> roles = new ArrayList<>();
        roles.add(userRepository.getRoleByName("Admin"));

        User existingUser = userRepository.getUserByUsername("admin");
        if(existingUser != null)
            return;

        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword(bCryptPasswordEncoder.encode("admin"));
        adminUser.setEmail("admin@admin.com");
        adminUser.setFirstName("System");
        adminUser.setPhone("123-456-7890");
        adminUser.setLastName("Main User");
        adminUser.setRoles(roles);
        userRepository.save(adminUser);
    }
}

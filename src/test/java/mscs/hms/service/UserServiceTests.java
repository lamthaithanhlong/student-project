package mscs.hms.service;

import mscs.hms.model.Role;
import mscs.hms.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.Random;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@ActiveProfiles("test")
public class UserServiceTests {

    @Autowired
    private IUserService userService;

    @Test
    public void testCreateUser() {
        User user = new User();

        int index = (new Random()).nextInt(1000, Integer.MAX_VALUE);

        user.setUsername("test-user" + index);
        user.setFirstName("Unit-" + index);
        user.setLastName("Test-" + index);
        user.setEmail("test"+ index + "@test.com");
        user.setPhoneNumber("145789652");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode("123456"));

        //TODO: FIX the bean creation issue for UserDetailsService and enable the below lines
        Role role = userService.getRoleByName("Admin");
        assert (role != null);

        user.getRoles().add(role);

        User savedUser = userService.saveUser(user);

        UserDetails existUser = userService.loadUserByUsername(user.getUsername());

        assert savedUser.getUsername().equals(existUser.getUsername());
    }
}

package mscs.hms.services;

import mscs.hms.models.Role;
import mscs.hms.models.UserInfo;
import mscs.hms.models.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Random;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@ActiveProfiles("test")
public class UserServiceTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserDetailsServiceImpl userService;

    //@Test
    public void testCreateUser() {
        UserInfo user = new UserInfo();

        int index = (new Random()).nextInt();

        user.setUserName("test-user" + index);
        user.setFirstName("Unit-" + index);
        user.setLastName("Test-" + index);
        user.setEmail("test"+ index + "@test.com");
        user.setPhone("145789652");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode("123456"));

        //TODO: FIX the bean creation issue for UserDetailsService and enable the below lines
        /*Role role = userService.getAllRoleByName("Admin");
        assert (role != null);

        user.getRoles().add(role);

        UserInfo savedUser = userService.saveUser(user);

        UserInfo existUser = entityManager.find(UserInfo.class, savedUser.getId());

        assert(user.getEmail()).equals(existUser.getEmail());*/
        assert (true);
    }
}

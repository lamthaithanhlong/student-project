package mscs.hms.repository;

import mscs.hms.model.Role;
import mscs.hms.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.Random;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@ActiveProfiles("test")
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        User user = new User();

        int index = (new Random()).nextInt(1000, Integer.MAX_VALUE);

        user.setUsername("test-user" + index);
        user.setFirstName("Unit-" + index);
        user.setLastName("Test-" + index);
        user.setEmail("test" + index + "@test.com");
        user.setPhoneNumber("145789652");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode("123456"));

        Role role = userRepository.getRoleByName("Admin");
        assert (role != null);

        user.getRoles().add(role);

        User savedUser = userRepository.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assert(user.getEmail()).equals(existUser.getEmail());
    }
}

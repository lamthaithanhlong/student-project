package mscs.hms.repository;

import mscs.hms.model.Role;
import mscs.hms.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Qualifier("users")
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User getUserByUsername(String username);

    @Query("SELECT r FROM Role r")
    List<Role> getAllRoles();

    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    Role getRoleByName(String name);

    List<User> findAllByRolesContainingIgnoreCase(String roleName);

    @Query("select u from User u where u.username = :searchString or u.lastName = :searchString  or u.firstName = :searchString  or u.email = :searchString or u.phoneNumber = :searchString ")
    Page<User> searchUser(String searchString, PageRequest pageRequest);

}
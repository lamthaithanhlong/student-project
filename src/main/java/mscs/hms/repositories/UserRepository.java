package mscs.hms.repositories;

import mscs.hms.entity.Role;
import mscs.hms.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("users")
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public User getUserByUsername(String username);

    @Query("SELECT r FROM Role r")
    public List<Role> getAllRoles();

    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    public Role getRoleByName(String name);

}
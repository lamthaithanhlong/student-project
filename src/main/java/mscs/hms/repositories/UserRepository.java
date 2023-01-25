package mscs.hms.repositories;

import mscs.hms.models.Role;
import mscs.hms.models.UserInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("users")
public interface UserRepository extends JpaRepository<UserInfo, Long> {

    @Query("SELECT u FROM UserInfo u WHERE u.userName = ?1")
    public Optional<UserInfo> getUserByUsername(String username);

    @Query("SELECT r FROM Role r")
    public List<Role> getAllRoles();

    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    public Role getAllRoleByName(String name);

}
package mscs.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mscs.hms.model.Role;


public interface RoleRepository extends JpaRepository<Role, Integer>{
    
}

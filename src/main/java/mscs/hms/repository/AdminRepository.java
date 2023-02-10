package mscs.hms.repository;

import mscs.hms.model.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    public Page<Admin> findByNameContainsIgnoreCase(String text, PageRequest pageRequest);
}

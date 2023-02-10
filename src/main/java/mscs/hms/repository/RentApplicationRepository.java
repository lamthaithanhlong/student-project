package mscs.hms.repository;

import mscs.hms.model.RentApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentApplicationRepository extends JpaRepository<RentApplication, Integer> {
    public Page<RentApplication> findByTitleOrApplicationIdOrStatusContainsIgnoreCase(String title, String applicationId, String status, PageRequest pageRequest);
}

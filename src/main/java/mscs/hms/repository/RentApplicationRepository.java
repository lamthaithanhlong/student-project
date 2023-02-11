package mscs.hms.repository;

import mscs.hms.model.RentApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RentApplicationRepository extends JpaRepository<RentApplication, Integer> {
    @Query("select r from RentApplication r where r.tenant.name = :searchString or r.landlord.name = :searchString or r.property.name = :searchString or " +
            "r.title = :searchString or r.applicationId = :searchString or r.status = :searchString")
    Page<RentApplication> searchRentApplication(String searchString, PageRequest pageRequest);
}

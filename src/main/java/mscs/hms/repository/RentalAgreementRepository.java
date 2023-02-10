package mscs.hms.repository;

import mscs.hms.model.RentalAgreement;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface RentalAgreementRepository extends JpaRepository<RentalAgreement, Integer> {
    @Query("select r from RentalAgreement r where r.property.name =:searchString or r.title =:searchString or r.contract =:searchString or r.endDate =:date or " +
            "r.landlord.name =:searchString or r.preparedDate =:date or r.tenant.name =:searchString")
    Page<RentalAgreement> searchRentalAgreement(String searchString, LocalDate date, PageRequest pageRequest);
}

package mscs.hms.repository;

import mscs.hms.model.RentalAgreement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface RentalAgreementRepository extends JpaRepository<RentalAgreement, Integer> {
    @Query("select r from RentalAgreement r where r.property.name =:searchString or r.title =:searchString or r.contract =:searchString or " +
            "r.landlord.name =:searchString or r.tenant.name =:searchString")
    Page<RentalAgreement> searchRentalAgreement(String searchString, PageRequest pageRequest);

    Page<RentalAgreement> searchByPreparedDateOrEndDateOrSignedDateOrStartDate(LocalDate preparedDate, LocalDate endDate, LocalDate signedDate, LocalDate startDate, PageRequest pageRequest);
}

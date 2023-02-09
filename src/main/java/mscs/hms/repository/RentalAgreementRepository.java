package mscs.hms.repository;

import mscs.hms.model.RentalAgreement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalAgreementRepository extends JpaRepository<RentalAgreement, Integer> {
    public Page<RentalAgreement> findByTitleContainsIgnoreCase(String text, PageRequest pageRequest);
}

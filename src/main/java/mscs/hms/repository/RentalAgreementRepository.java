package mscs.hms.repository;

import mscs.hms.model.RentalAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalAgreementRepository extends JpaRepository<RentalAgreement, Integer> {
}

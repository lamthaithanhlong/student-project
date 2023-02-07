package mscs.hms.repository;

import mscs.hms.model.Landlord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandlordRepository extends JpaRepository<Landlord, Integer> {
}

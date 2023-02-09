package mscs.hms.repository;

import mscs.hms.model.Landlord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandlordRepository extends JpaRepository<Landlord, Integer> {
    public Page<Landlord> findByNameContainsIgnoreCase(String text, PageRequest pageRequest);
}

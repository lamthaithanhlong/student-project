package mscs.hms.repository;

import mscs.hms.model.Landlord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LandlordRepository extends JpaRepository<Landlord, Integer> {
    @Query("select l from Landlord l, Property p, LegalEntity le where l.legalEntity = le and l.name =:text or p.name =:text or le.legalEntityName =:text")
    Page<Landlord> searchLandlord(String text, PageRequest pageRequest);
}

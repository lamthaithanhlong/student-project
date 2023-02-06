package mscs.hms.repository;

import mscs.hms.model.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preference, Integer> {
}

package mscs.hms.repository;

import mscs.hms.entity.Preference;
import org.springframework.data.repository.CrudRepository;

public interface PreferenceRepository extends CrudRepository<Preference, Integer> {
}

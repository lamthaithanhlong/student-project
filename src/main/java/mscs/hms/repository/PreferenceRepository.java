package mscs.hms.repository;

import mscs.hms.model.Preference;
import org.springframework.data.repository.CrudRepository;

public interface PreferenceRepository extends CrudRepository<Preference, Integer> {
}

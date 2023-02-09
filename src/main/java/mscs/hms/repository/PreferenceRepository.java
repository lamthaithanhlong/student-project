package mscs.hms.repository;

import mscs.hms.model.Preference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preference, Integer> {
    public Page<Preference> findByTitleContainsIgnoreCase(String text, PageRequest pageRequest);
}

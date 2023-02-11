package mscs.hms.repository;

import mscs.hms.model.Preference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preference, Integer> {
    Page<Preference> findByNoOfRoomsOrNoOfBathRooms(Integer noOfRooms, Integer noOfBathRooms, PageRequest pageRequest);

    Page<Preference> findByTitleContainsIgnoreCase(String title, PageRequest pageRequest);
}

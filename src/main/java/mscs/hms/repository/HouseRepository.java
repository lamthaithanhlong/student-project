package mscs.hms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import mscs.hms.model.House;

public interface HouseRepository extends JpaRepository<House, Integer> {
    Page<House> findByLandExtentOrNoOfRoomsOrNoOfBathRooms(Double landExtent, Integer noOfRooms, Integer noOfBathrooms, PageRequest pageRequest);

    Page<House> findByNameContainsIgnoreCase(String name, PageRequest pageRequest);
}

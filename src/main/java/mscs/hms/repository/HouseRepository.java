package mscs.hms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import mscs.hms.model.House;
import org.springframework.data.jpa.repository.Query;

public interface HouseRepository extends JpaRepository<House, Integer> {
//    @Query(value="select h from House h , Address a where h.Address = a and a.")
    Page<House> findByNameOrLandExtentOrNoOfRoomsOrNoOfBathRoomsContainsIgnoreCase(String name, String landExtent, String noOfRooms, String noOfBathrooms, PageRequest pageRequest);
}

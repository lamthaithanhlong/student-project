package mscs.hms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import mscs.hms.model.Apartment;

public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {
    Page<Apartment> findByNoOfRoomsOrNoOfBathRooms(Integer noOfRooms, Integer noOfBathRooms, PageRequest pageRequest);

    Page<Apartment> findByNameContainsIgnoreCase(String searchString, PageRequest pageRequest);}

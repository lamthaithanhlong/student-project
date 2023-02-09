package mscs.hms.repository;

import mscs.hms.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    public Page<Address> findByCityContainingIgnoreCase(String text, PageRequest pageRequest);
}

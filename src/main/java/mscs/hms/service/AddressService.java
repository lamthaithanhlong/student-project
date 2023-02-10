package mscs.hms.service;

import mscs.hms.model.Address;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AddressService {
    Address saveAddress(Address address);
    Address get(Integer id);
    void delete(Integer id);
    List<Address> findAll();
    Address save(Address address);
    Page<Address> getAll(String searchString, Integer page, Integer pageSize);
}

package mscs.hms.service;

import mscs.hms.model.Address;

public interface AddressService {
    public Address saveAddress(Address address);
    public Address get(Integer id);
    public void delete(Integer id);
    public Iterable<Address> findAll();
    public Address save(Address address);
}

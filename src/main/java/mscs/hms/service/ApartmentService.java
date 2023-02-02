package mscs.hms.service;

import mscs.hms.entity.Apartment;

public interface ApartmentService {
    public Apartment saveApartment(Apartment apartment);
    public Apartment get(Integer id);
    public void delete(Integer id);
    public Iterable<Apartment> findAll();
    public Apartment save(Apartment apartment);
}

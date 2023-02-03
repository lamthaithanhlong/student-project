package mscs.hms.service;

import mscs.hms.model.House;

public interface HouseService {
    public House saveHouse(House house);
    public House get(Integer id);
    public void delete(Integer id);
    public Iterable<House> findAll();
    public House save(House house);
}

package mscs.hms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mscs.hms.model.House;
import mscs.hms.repository.HouseRepository;
import mscs.hms.service.HouseService;

@Service
public class HouseServiceImpl extends AbsBaseService implements HouseService {
    
    @Autowired
    HouseRepository houseRepository;    

    @Override
    public House saveHouse(House house) {
        return houseRepository.save(house);
    }

    @Override
    public Iterable<House> findAll() {
        return houseRepository.findAll();
    }

    @Override
    public House get(Integer id) {
        return houseRepository.findById(id).orElse(null);
    }

    @Override
    public House save(House house) {
        return houseRepository.save(house);
    }

    @Override
    public void delete(Integer id) {
        houseRepository.deleteById(id);
    }
}

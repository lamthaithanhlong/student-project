package mscs.hms.service.impl;

import mscs.hms.model.Address;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<House> getAll(String searchString, Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        if(searchString == null || searchString.isBlank()) {
            return houseRepository.findAll(pageRequest);
        } else if (NumberUtils.isParsable(searchString)) {
            return houseRepository.findByLandExtentOrNoOfRoomsOrNoOfBathRooms(Double.parseDouble(searchString),Integer.parseInt(searchString),Integer.parseInt(searchString), pageRequest);
        } else
            return houseRepository.findByNameContainsIgnoreCase(searchString, pageRequest);
    }
}

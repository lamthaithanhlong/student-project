package mscs.hms.service.impl;

import mscs.hms.model.Address;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import mscs.hms.model.Apartment;
import mscs.hms.repository.ApartmentRepository;
import mscs.hms.service.ApartmentService;

@Service
public class ApartmentServiceImpl extends AbsBaseService implements ApartmentService {
    
    @Autowired
    ApartmentRepository apartmentRepository;    

    @Override
    public Apartment saveApartment(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    @Override
    public Iterable<Apartment> findAll() {
        return apartmentRepository.findAll();
    }

    @Override
    public Apartment get(Integer id) {
        return apartmentRepository.findById(id).orElse(null);
    }

    @Override
    public Apartment save(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    @Override
    public void delete(Integer id) {
        apartmentRepository.deleteById(id);
    }

    public Page<Apartment> getAll(String searchString, Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page,pageSize);
        if(searchString == null || searchString.isBlank()) {
            return apartmentRepository.findAll(pageRequest);
        } else if (NumberUtils.isParsable(searchString)) {
            return apartmentRepository.findByNoOfRoomsOrNoOfBathRooms(Integer.parseInt(searchString),Integer.parseInt(searchString), pageRequest);
        } else
            return apartmentRepository.findByNameContainsIgnoreCase(searchString, pageRequest);
    }
}

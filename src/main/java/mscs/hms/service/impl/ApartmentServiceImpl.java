package mscs.hms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
}

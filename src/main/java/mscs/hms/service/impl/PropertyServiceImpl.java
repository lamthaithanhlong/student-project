package mscs.hms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mscs.hms.entity.Apartment;
import mscs.hms.entity.House;
import mscs.hms.entity.Property;
import mscs.hms.repository.ApartmentRepository;
import mscs.hms.repository.HouseRepository;
import mscs.hms.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    ApartmentRepository apartmentRepository;

    @Override
    public Property saveProperty(Property property) {
        if(property.getClass() == House.class)
            return houseRepository.save((House)property);
        else
            return apartmentRepository.save((Apartment)property);
    }
    
}

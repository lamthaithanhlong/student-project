package mscs.hms.service.impl;

import mscs.hms.model.Landlord;
import mscs.hms.repository.LandlordRepository;
import mscs.hms.service.LandlordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class LandlordServiceImpl implements LandlordService {

    @Autowired
    private LandlordRepository landlordRepository;

    @Override
    public Landlord save(Landlord landlord) {
        return landlordRepository.save(landlord);
    }

    @Override
    public Landlord getById(Integer id) {
        return landlordRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        landlordRepository.deleteById(id);
    }

    @Override
    public List<Landlord> findAll() {
        return landlordRepository.findAll();
    }
}

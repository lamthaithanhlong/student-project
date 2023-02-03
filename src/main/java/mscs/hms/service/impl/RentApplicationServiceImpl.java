package mscs.hms.service.impl;

import mscs.hms.model.RentApplication;
import mscs.hms.repository.RentApplicationRepository;
import mscs.hms.service.RentApplicationService;
import org.springframework.beans.factory.annotation.Autowired;

public class RentApplicationServiceImpl implements RentApplicationService {
    @Autowired
    RentApplicationRepository rentApplicationRepository;

    @Override
    public RentApplication save(RentApplication rentApplication) {
        return rentApplicationRepository.save(rentApplication);
    }

    @Override
    public RentApplication getById(Integer id) {
        return rentApplicationRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        rentApplicationRepository.deleteById(id);
    }

    @Override
    public Iterable<RentApplication> findAll() {
        return rentApplicationRepository.findAll();
    }
}

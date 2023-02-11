package mscs.hms.service.impl;

import mscs.hms.model.RentApplication;
import mscs.hms.repository.RentApplicationRepository;
import mscs.hms.service.RentApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
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
    public List<RentApplication> findAll() {
        return rentApplicationRepository.findAll();
    }

    public Page<RentApplication> getAll(String searchString, Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page,pageSize);
        if(searchString == null || searchString.isBlank())
            return rentApplicationRepository.findAll(pageRequest);
        else
            return rentApplicationRepository.searchRentApplication(searchString.toLowerCase(), pageRequest);
    }
}

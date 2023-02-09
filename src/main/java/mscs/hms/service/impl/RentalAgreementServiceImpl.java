package mscs.hms.service.impl;

import mscs.hms.model.Property;
import mscs.hms.model.RentalAgreement;
import mscs.hms.repository.RentalAgreementRepository;
import mscs.hms.service.RentalAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RentalAgreementServiceImpl implements RentalAgreementService {
    @Autowired
    private RentalAgreementRepository rentalAgreementRepository;

    @Override
    public RentalAgreement save(RentalAgreement rentalAgreement) {
        return rentalAgreementRepository.save(rentalAgreement);
    }

    @Override
    public RentalAgreement getById(Integer id) {
        return rentalAgreementRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        rentalAgreementRepository.deleteById(id);
    }

    @Override
    public List<RentalAgreement> findAll() {
        return rentalAgreementRepository.findAll();
    }

    public Page<RentalAgreement> getAll(String searchString, Integer pageSize, Integer offset) {
        PageRequest pageRequest = PageRequest.of(offset,pageSize);
        if(searchString == null || searchString.isBlank())
            return rentalAgreementRepository.findAll(pageRequest);
        else
            return rentalAgreementRepository.findByTitleContainsIgnoreCase(searchString, pageRequest);
    }
}

package mscs.hms.service.impl;

import mscs.hms.model.RentalAgreement;
import mscs.hms.repository.RentalAgreementRepository;
import mscs.hms.service.RentalAgreementService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static org.apache.commons.validator.GenericValidator.isDate;

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

    public Page<RentalAgreement> getAll(String searchString, Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page,pageSize);
        if(searchString == null || searchString.isBlank()) {
            return rentalAgreementRepository.findAll(pageRequest);
        } else if (isDate(searchString, "YYYY-MM-DD",true)) {
            return rentalAgreementRepository.searchByPreparedDateOrEndDateOrSignedDateOrStartDate(LocalDate.parse(searchString), LocalDate.parse(searchString), LocalDate.parse(searchString), LocalDate.parse(searchString), pageRequest);
        } else {
            return rentalAgreementRepository.searchRentalAgreement(searchString.toLowerCase(), pageRequest);
        }
    }
}

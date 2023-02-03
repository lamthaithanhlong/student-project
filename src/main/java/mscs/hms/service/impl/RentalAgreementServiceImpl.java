package mscs.hms.service.impl;

import mscs.hms.model.RentalAgreement;
import mscs.hms.repository.RentalAgreementRepository;
import mscs.hms.service.RentalAgreementService;
import org.springframework.beans.factory.annotation.Autowired;

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
    public Iterable<RentalAgreement> findAll() {
        return rentalAgreementRepository.findAll();
    }
}

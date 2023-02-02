package mscs.hms.service;

import mscs.hms.entity.RentalAgreement;

public interface RentalAgreementService {
    public RentalAgreement save(RentalAgreement rentalAgreement);
    public RentalAgreement getById(Integer id);
    public void deleteById(Integer id);
    public Iterable<RentalAgreement> findAll();
}

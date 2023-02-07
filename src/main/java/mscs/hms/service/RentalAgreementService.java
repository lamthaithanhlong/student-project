package mscs.hms.service;

import mscs.hms.model.RentalAgreement;
import java.util.List;

public interface RentalAgreementService {
    public RentalAgreement save(RentalAgreement rentalAgreement);
    public RentalAgreement getById(Integer id);
    public void deleteById(Integer id);
    public List<RentalAgreement> findAll();
}

package mscs.hms.service;

import mscs.hms.model.RentalAgreement;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RentalAgreementService {
    public RentalAgreement save(RentalAgreement rentalAgreement);
    public RentalAgreement getById(Integer id);
    public void deleteById(Integer id);
    public List<RentalAgreement> findAll();
    public Page<RentalAgreement> getAll(String searchString, Integer page, Integer pageSize);
}

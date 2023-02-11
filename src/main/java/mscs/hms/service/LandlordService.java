package mscs.hms.service;

import mscs.hms.model.Landlord;
import org.springframework.data.domain.Page;

import java.util.List;
public interface LandlordService {
    public Landlord save(Landlord landlord);
    public Landlord getById(Integer id);
    public void deleteById(Integer id);
    public List<Landlord> findAll();
    public Page<Landlord> getAll(String searchString, Integer page, Integer pageSize);
}

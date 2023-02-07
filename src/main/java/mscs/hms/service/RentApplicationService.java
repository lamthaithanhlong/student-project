package mscs.hms.service;


import mscs.hms.model.RentApplication;
import java.util.List;

public interface RentApplicationService {
    public RentApplication save(RentApplication rentApplication);
    public RentApplication getById(Integer id);
    public void deleteById(Integer id);
    public List<RentApplication> findAll();
}

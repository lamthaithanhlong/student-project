package mscs.hms.service;

import mscs.hms.model.LegalEntity;
import java.util.List;

public interface LegalEntityService {
    public LegalEntity get(Integer id);
    public List<? extends LegalEntity> findAll();
}

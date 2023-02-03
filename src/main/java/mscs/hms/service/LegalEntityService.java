package mscs.hms.service;

import mscs.hms.model.LegalEntity;

public interface LegalEntityService {
    public LegalEntity get(Integer id);
    public Iterable<? extends LegalEntity> findAll();
}

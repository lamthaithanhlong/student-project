package mscs.hms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mscs.hms.entity.LegalEntity;
import mscs.hms.service.LegalEntityService;
import mscs.hms.repository.PersonRepository;
import mscs.hms.repository.CompanyRepository;
import java.util.List;
import java.util.ArrayList;

@Service
public class LegalEntityServiceImpl extends AbsBaseService implements LegalEntityService {
    
    @Autowired
    PersonRepository personRepository;
    
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public Iterable<? extends LegalEntity> findAll() {
        List<LegalEntity> legalEntities = new ArrayList<>();
        personRepository.findAll().forEach(x -> legalEntities.add(x));
        companyRepository.findAll().forEach(x -> legalEntities.add(x));
        return legalEntities;
    }

    @Override
    public LegalEntity get(Integer id) {
        return personRepository.findById(id).orElse(null);
    }
}

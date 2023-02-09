package mscs.hms.service.impl;

import mscs.hms.model.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import mscs.hms.model.LegalEntity;
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
    public List<? extends LegalEntity> findAll() {
        List<LegalEntity> legalEntities = new ArrayList<>();
        personRepository.findAll().forEach(legalEntities::add);
        companyRepository.findAll().forEach(legalEntities::add);
        return legalEntities;
    }

    @Override
    public LegalEntity get(Integer id) {
        LegalEntity legalEntity = personRepository.findById(id).orElse(null);
        if(legalEntity == null){
            legalEntity = companyRepository.findById(id).orElse(null);
        }
        return legalEntity;
    }

    public Page<? extends LegalEntity> getAll(String searchString, Integer pageSize, Integer offset) {
        PageRequest pageRequest = PageRequest.of(offset,pageSize);
        if(searchString == null || searchString.isBlank())
            return personRepository.findAll(pageRequest);
        else
            return personRepository.findByFirstNameContainsIgnoreCase(searchString, pageRequest);
    }
}

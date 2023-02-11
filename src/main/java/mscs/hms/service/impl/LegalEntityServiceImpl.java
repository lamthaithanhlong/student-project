package mscs.hms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import mscs.hms.model.LegalEntity;
import mscs.hms.model.Company;
import mscs.hms.model.Person;
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

    public Page<? extends LegalEntity> getAll(String searchString, Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Company> resultCompany;
        Page<Person> resultPerson;
        //TODO: Query only the remainig page size for person
        if (searchString == null || searchString.isBlank()){
            resultCompany = companyRepository.findAll(pageRequest);
            resultPerson = personRepository.findAll(pageRequest);
        }
        else{
            resultCompany = companyRepository.searchCompany(searchString.toLowerCase(), pageRequest);
            resultPerson = personRepository.searchPerson(searchString.toLowerCase(), pageRequest);
        }
        List<LegalEntity> resultList = new ArrayList<>();
        resultList.addAll(resultCompany.getContent());
        if(resultCompany.getNumberOfElements() < pageSize)
        {
            int remainigPageSize = pageSize - resultCompany.getNumberOfElements();
            List<Person> personContent = resultPerson.getContent();
            resultList.addAll(personContent.subList(0, remainigPageSize <= personContent.size() ? remainigPageSize : personContent.size()));
        }
        final Page<LegalEntity> pageResult = new PageImpl<>(resultList, pageRequest, resultCompany.getTotalElements() + resultPerson.getTotalElements());
        
        return pageResult;
    }
}

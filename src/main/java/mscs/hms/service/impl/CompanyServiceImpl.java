package mscs.hms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mscs.hms.entity.Company;
import mscs.hms.repository.CompanyRepository;
import mscs.hms.service.CompanyService;

@Service
public class CompanyServiceImpl extends AbsBaseService implements CompanyService {
    
    @Autowired
    CompanyRepository companyRepository;    

    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Iterable<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company get(Integer id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void delete(Integer id) {
        companyRepository.deleteById(id);
    }
}

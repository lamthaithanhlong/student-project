package mscs.hms.service;

import mscs.hms.model.Company;
import org.springframework.data.domain.Page;

public interface CompanyService {
    public Company saveCompany(Company company);
    public Company get(Integer id);
    public void delete(Integer id);
    public Iterable<Company> findAll();
    public Company save(Company company);
    public Page<Company> getAll(String searchString, Integer page, Integer pageSize);
}

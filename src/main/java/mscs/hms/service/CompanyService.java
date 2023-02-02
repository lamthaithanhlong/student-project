package mscs.hms.service;

import mscs.hms.entity.Company;

public interface CompanyService {
    public Company saveCompany(Company company);
    public Company get(Integer id);
    public void delete(Integer id);
    public Iterable<Company> findAll();
    public Company save(Company company);
}

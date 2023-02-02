package mscs.hms.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;

@Entity
@DiscriminatorValue("Company")
public class Company extends LegalEntity{
    @NotEmpty
    private String companyName;
    public Company() {
    }    
    public Company(Integer id, String phoneNumber, @NotEmpty String companyName) {
        super(id, phoneNumber);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    @Override
    public String getLegalEntityName() {
        return getCompanyName();
    }
    @Override
    public String toString() {
        return "Company [Legal Entity=" + super.toString() +  ", companyName=" + companyName + "]";
    } 
}

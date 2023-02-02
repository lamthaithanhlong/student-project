package mscs.hms.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import mscs.hms.entity.constraints.PhoneNumberConstraint;

@MappedSuperclass
public abstract class LegalEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @PhoneNumberConstraint
    private String phoneNumber;
    @Transient
    private String legalEntityName;
    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @Nullable
    private User systemUser;
    public LegalEntity() {
    }
    
    public LegalEntity(Integer id, String phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getLegalEntityName() {
        return legalEntityName;
    }
    public void setLegalEntityName(String legalEntityName) {        
    }

    public User getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(User systemUser) {
        this.systemUser = systemUser;
    }

    @Override
    public String toString() {
        return "LegalEntity [id=" + id + ", phoneNumber=" + phoneNumber + ", legalEntityName=" + legalEntityName + ", systemUser=" + systemUser + "]";
    }    
}

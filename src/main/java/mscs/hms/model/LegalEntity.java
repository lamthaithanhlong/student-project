package mscs.hms.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import mscs.hms.dto.constraints.PhoneNumberConstraint;

//@MappedSuperclass
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="legalEntity_type", discriminatorType=DiscriminatorType.STRING)
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

    @OneToOne
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

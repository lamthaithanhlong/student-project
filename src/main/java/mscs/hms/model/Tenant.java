package mscs.hms.model;

import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Tenant {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Inquiry> inquiries;

    @ManyToMany
    private List<Property> properties;

    @OneToMany
    private List<RentApplication> rentApplications;

    @OneToOne
    private Preference preferences;

    @OneToOne
    private LegalEntity legalEntity;

    @ManyToMany
    private List<RentalAgreement> rentalAgreements;
}

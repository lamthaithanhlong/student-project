package mscs.hms.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Tenant {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Inquiry> inquiries;

    @ManyToMany
    private List<Property> property;

    @OneToMany
    private List<RentApplication> rentApplication;

    @OneToOne
    private Preference preference;

    @OneToOne
    private RentalAgreement rentalAgreement;
}

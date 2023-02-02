package mscs.hms.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Landlord {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany
    private List<Inquiry> inquiries;

    @ManyToMany
    private List<RentalAgreement> rentalAgreements;

    @OneToMany
    private List<Property> properties;
}

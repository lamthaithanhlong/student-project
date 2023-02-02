package mscs.hms.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class RentalAgreement {
    @Id
    @GeneratedValue
    private Long id;
    private String agreementId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate signedDate;
    private LocalDate preparedDate;
    private String contract;

//    @OneToOne
//    private Property property;

    @OneToOne
    private Tenant tenant;

    @ManyToMany
    private List<Landlord> landlords;

}

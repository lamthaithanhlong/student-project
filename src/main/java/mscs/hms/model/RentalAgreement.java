package mscs.hms.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
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

    @OneToOne
    private Property property;

    @OneToOne
    private Tenant tenant;

    @ManyToMany
    private List<Landlord> landlords;

}

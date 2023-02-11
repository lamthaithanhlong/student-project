package mscs.hms.model;

import jakarta.persistence.*;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class RentalAgreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Title should not be Empty")
    private String title;
    private String agreementId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate signedDate;
    private LocalDate preparedDate;
    private String contract;

    @OneToOne
    @JoinColumn(name = "propertyId", referencedColumnName = "id")
    private Property property;

    @OneToOne
    @JoinColumn(name = "tenantId", referencedColumnName = "id")
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "landlordId", referencedColumnName = "id")
    private Landlord landlord;

}

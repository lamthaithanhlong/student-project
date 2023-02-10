package mscs.hms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class RentApplication {
    @Id
    @GeneratedValue
    private Integer id;
    @NotEmpty(message = "Title should not be Empty")
    private String title;
    private String applicationId;
    private String status;

    @ManyToOne
    @JoinColumn(name = "landlordId", referencedColumnName = "id")
    private Landlord landlord;

    @ManyToOne
    @JoinColumn(name = "tenantId", referencedColumnName = "id")
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "propertyId", referencedColumnName = "id")
    private Property property;
}

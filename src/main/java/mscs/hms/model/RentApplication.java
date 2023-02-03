package mscs.hms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    private String applicationId;
    private String status;

    @ManyToOne
    private Landlord landlord;

    @ManyToOne
    private Tenant tenant;
}

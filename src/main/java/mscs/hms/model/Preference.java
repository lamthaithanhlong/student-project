package mscs.hms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import mscs.hms.model.constraints.PositiveNumberConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Preference {
    @Id
    @GeneratedValue
    private Long id;
    @PositiveNumberConstraint
    private Integer noOfRooms;
    @PositiveNumberConstraint
    private Integer noOfBathRooms;

    @OneToOne
    private Tenant tenant;
}

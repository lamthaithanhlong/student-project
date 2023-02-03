package mscs.hms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import mscs.hms.dto.constraints.PositiveNumberConstraint;

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

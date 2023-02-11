package mscs.hms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import mscs.hms.model.constraints.PositiveNumberConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Preference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Title should not be Empty")
    private String title;

    @PositiveNumberConstraint
    private Integer noOfRooms;
    @PositiveNumberConstraint
    private Integer noOfBathRooms;

    @OneToOne
    private Tenant tenant;
}

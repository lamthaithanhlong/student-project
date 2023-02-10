package mscs.hms.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import mscs.hms.model.constraints.PositiveNumberConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@DiscriminatorValue("House")
public class House extends Property {
    @PositiveNumberConstraint
    private double landExtent;
}

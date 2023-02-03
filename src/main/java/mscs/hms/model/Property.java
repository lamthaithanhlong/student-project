package mscs.hms.model;

import jakarta.persistence.*;
import mscs.hms.model.constraints.PositiveNumberConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
//@MappedSuperclass
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="property_type", discriminatorType=DiscriminatorType.STRING)
public abstract class Property {
    @Id
    @GeneratedValue
    private Integer id;
    @PositiveNumberConstraint
    private Integer noOfRooms;
    @PositiveNumberConstraint
    private Integer noOfBathRooms;
    @OneToOne
    private Address address;
}

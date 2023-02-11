package mscs.hms.model;

import jakarta.persistence.*;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Landlord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    @JoinTable(
            name="landlord_property",
            joinColumns={@JoinColumn(name="propertyId", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="landlordId", referencedColumnName="id")})
    private List<Property> properties;
    @NotEmpty(message = "Name should not be Empty")
    private String name;
    @OneToOne
    @JoinColumn(name = "legalEntityId", referencedColumnName = "id")
    private LegalEntity legalEntity;    
}

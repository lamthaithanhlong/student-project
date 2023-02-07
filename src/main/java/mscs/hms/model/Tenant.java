package mscs.hms.model;

import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Tenant {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(
            name="tenant_property",
            joinColumns={@JoinColumn(name="propertyId", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="tenantId", referencedColumnName="id")})
    private List<Property> properties;

    @OneToOne
    @JoinColumn(name = "preferenceId", referencedColumnName = "id")
    private Preference preferences;

    @OneToOne
    @JoinColumn(name = "legalEntityId", referencedColumnName = "id")
    private LegalEntity legalEntity;    
}

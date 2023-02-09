package mscs.hms.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Admin {
    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty(message = "Name should not be Empty")
    private String name;

    @OneToOne
    @JoinColumn(name = "legalEntityId", referencedColumnName = "id")
    private LegalEntity legalEntity;    
}

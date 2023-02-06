package mscs.hms.model;

import jakarta.persistence.*;

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

    @OneToOne
    private LegalEntity legalEntity;    
}

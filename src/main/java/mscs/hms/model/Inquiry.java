package mscs.hms.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Inquiry {
    @Id
    @GeneratedValue
    private Integer id;
    private LocalDate inquiryDate;
    private String inquiryDetails;
    private String reply;

    @ManyToOne
    private Tenant tenant;

    @ManyToOne
    private Landlord landlord;

    @OneToOne
    private Property property;
}

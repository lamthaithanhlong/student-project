package mscs.hms.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

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

package mscs.hms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private Integer id;
    private String streetName;
    private String city;
    private String state;
    private String zip;
}

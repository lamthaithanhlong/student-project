package mscs.hms.entity;

import jakarta.persistence.*;
import mscs.hms.entity.constraints.PositiveNumberConstraint;

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
    
    public Property() {
    }
    public Property(Integer id, Integer noOfRooms, Integer noOfBathRooms) {
        this.id = id;
        this.noOfRooms = noOfRooms;
        this.noOfBathRooms = noOfBathRooms;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getNoOfRooms() {
        return noOfRooms;
    }
    public void setNoOfRooms(Integer noOfRooms) {
        this.noOfRooms = noOfRooms;
    }
    public Integer getNoOfBathRooms() {
        return noOfBathRooms;
    }
    public void setNoOfBathRooms(Integer noOfBathRooms) {
        this.noOfBathRooms = noOfBathRooms;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @OneToOne
    private Address address;
}

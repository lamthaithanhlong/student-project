package mscs.hms.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import mscs.hms.entity.constraints.PositiveNumberConstraint;

@MappedSuperclass
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

    @OneToOne
    private Address address;
}

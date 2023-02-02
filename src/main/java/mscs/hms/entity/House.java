package mscs.hms.entity;

import jakarta.persistence.Entity;
import mscs.hms.entity.constraints.PositiveNumberConstraint;

@Entity
public class House extends Property {
    @PositiveNumberConstraint
    private double landExtent;


    public House() {
    }

    public House(Integer id, Integer noOfRooms, Integer noOfBathRooms, Double landExtent) {
        super(id, noOfRooms, noOfBathRooms);
        this.landExtent = landExtent;
    }

    public Double getLandExtent() {
        return landExtent;
    }

    public void setLandExtent(Double landExtent) {
        this.landExtent = landExtent;
    }
    
}

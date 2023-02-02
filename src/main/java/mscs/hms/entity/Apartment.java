package mscs.hms.entity;

import jakarta.persistence.Entity;

@Entity
public class Apartment extends Property{
    private String name;

    public Apartment() {
    }

    public Apartment(Integer id, Integer noOfRooms, Integer noOfBathRooms, String name) {
        super(id, noOfRooms, noOfBathRooms);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

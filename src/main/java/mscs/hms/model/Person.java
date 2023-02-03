package mscs.hms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@DiscriminatorValue("Person")
public class Person extends LegalEntity{
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    public Person() {
    }
    public Person(Integer id, String phoneNumber, @NotEmpty String firstName, @NotEmpty String lastName) {
        super(id, phoneNumber);
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Override
    public String getLegalEntityName() {
        return lastName + ", " + firstName;
    }
    @Override
    public String toString() {
        return "Person [Legal Entity=" + super.toString() + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}

package mscs.hms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@DiscriminatorValue("Person")
public class Person extends LegalEntity{
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @Override
    public String getLegalEntityName() {
        if(lastName == null || lastName.isBlank()){
            if(firstName == null || firstName.isBlank()){
                return null;   
            }
            else {
                return firstName; 
            }
        }
        else if(firstName == null || firstName.isBlank()){
            return lastName;
        }
        return lastName + ", " + firstName;
    }
    @Override
    public String toString() {
        return "Person [Legal Entity=" + super.toString() + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}

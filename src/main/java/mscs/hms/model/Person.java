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
        return lastName + ", " + firstName;
    }
    @Override
    public String toString() {
        return "Person [Legal Entity=" + super.toString() + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}

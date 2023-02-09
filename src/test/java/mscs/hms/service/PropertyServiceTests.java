package mscs.hms.service;

import mscs.hms.model.Apartment;
import mscs.hms.model.House;
import mscs.hms.helper.PropertyFactory;
import mscs.hms.helper.PropertyType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@ActiveProfiles("test")
public class PropertyServiceTests {

    @Autowired
    private PropertyService propertyService;

    @Test
    public void testCreateProperty_House() {
        House house = (House)PropertyFactory.createProperty(PropertyType.House);
        house.setLandExtent(200.0);
        house.setNoOfBathRooms(1);
        house.setNoOfRooms(2);
        house.setName("Test");
        propertyService.saveProperty(house);
    }

    @Test
    public void testCreateProperty_Apartment() {
        Apartment apartment = (Apartment)PropertyFactory.createProperty(PropertyType.Apartment);
        apartment.setName("Test Case Name");
        apartment.setNoOfBathRooms(1);
        apartment.setNoOfRooms(2);
        propertyService.saveProperty(apartment);
    }
}

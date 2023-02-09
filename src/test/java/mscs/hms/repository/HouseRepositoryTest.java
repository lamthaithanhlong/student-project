package mscs.hms.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import mscs.hms.model.House;
import mscs.hms.helper.PropertyFactory;
import mscs.hms.helper.PropertyType;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@ActiveProfiles("test")
public class HouseRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private HouseRepository houseRepository;

    @Test
    public void testCreateProperty() {
        House house = (House)PropertyFactory.createProperty(PropertyType.House);
        house.setLandExtent(150.0);
        house.setName("Test");
        house.setNoOfBathRooms(1);
        house.setNoOfRooms(2);

        House savedHouse = houseRepository.save(house);

        House existHouse = entityManager.find(House.class, savedHouse.getId());

        assert(house.getNoOfBathRooms()).equals(existHouse.getNoOfBathRooms());
    }
}

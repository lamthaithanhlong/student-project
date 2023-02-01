package mscs.hms.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import mscs.hms.entity.Apartment;
import mscs.hms.entity.PropertyFactory;
import mscs.hms.entity.PropertyType;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
@ActiveProfiles("test")
public class ApartmentRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Test
    public void testCreateProperty() {
        Apartment apartment = (Apartment)PropertyFactory.createProperty(PropertyType.Apartment);
        apartment.setName("Test Name");
        apartment.setNoOfBathRooms(1);
        apartment.setNoOfRooms(2);

        Apartment savedApartment = apartmentRepository.save(apartment);

        Apartment existApartment = entityManager.find(Apartment.class, savedApartment.getId());

        assert(apartment.getNoOfBathRooms()).equals(existApartment.getNoOfBathRooms());
    }
}
